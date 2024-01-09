import json
import kafka
import dotenv
import os
from repo import Api

dotenv.load_dotenv()

api = Api(host="https://" + f"{os.environ['DB_HOST']}")

producer = kafka.KafkaProducer(
    bootstrap_servers=[f"localhost:{os.environ['KAFKA_PORT']}"],
    value_serializer=lambda x: json.dumps(x).encode("utf-8"),
)

consumer = kafka.KafkaConsumer(
    "result",
    bootstrap_servers=[f"localhost:{os.environ['KAFKA_PORT']}"],
    value_deserializer=lambda x: json.loads(x.decode("utf-8")),
    auto_offset_reset="latest",
)


for message in consumer:
    result = message.value
    # TODO: Fetch DB, Tạo Event
    print(result)
    labels = api.fetchCurrentLabels(result["customer_id"])
    if result["label_id"] in labels and result["is_match"] == False:
        body = {
            "customer_id": result["customer_id"],
            "label_id": result["label_id"],
            "event_type_id": 4,
            "result_id": result["result_id"],
        }
        event_id = api.addEvent(body)
        body["last_event_id"] = event_id
        body.pop("result_id")
        body.pop("event_type_id")
        producer.send(
            "event",
            value=body,
        )
    elif result["label_id"] not in labels and result["is_match"] == True:
        body = {
            "customer_id": result["customer_id"],
            "label_id": result["label_id"],
            "event_type_id": 1,
            "result_id": result["result_id"],
        }
        event_id = api.addEvent(body)
        body["last_event_id"] = event_id
        body.pop("result_id")
        body.pop("event_type_id")
        producer.send(
            "event",
            value=body,
        )
