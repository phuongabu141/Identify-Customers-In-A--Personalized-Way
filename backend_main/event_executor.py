import json
import kafka
import dotenv
import os
from repo import Api

dotenv.load_dotenv()

api = Api(host="https://" + f"{os.environ['DB_HOST']}")

consumer = kafka.KafkaConsumer(
    "event",
    bootstrap_servers=[f"localhost:{os.environ['KAFKA_PORT']}"],
    value_deserializer=lambda x: json.loads(x.decode("utf-8")),
    auto_offset_reset="latest",
)


for message in consumer:
    event = message.value
    print(api.addCustomerLabel(event))
