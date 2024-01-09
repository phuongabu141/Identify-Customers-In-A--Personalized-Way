import json
from models import *
from repo import Api
import kafka
import dotenv
import os
from fastapi import FastAPI


dotenv.load_dotenv()

app = FastAPI()

producer = kafka.KafkaProducer(
    bootstrap_servers=[f"localhost:{os.environ['KAFKA_PORT']}"],
    value_serializer=lambda x: json.dumps(x).encode("utf-8"),
)

api = Api(host="https://" + f"{os.environ['DB_HOST']}")

operator_dict = {
    1: lambda x, y: x > y,
    2: lambda x, y: x < y,
    3: lambda x, y: x == y,
    4: lambda x, y: x >= y,
    5: lambda x, y: x <= y,
    6: lambda x, y: x != y,
    7: lambda x, y: y in x,
    8: lambda x, y: y not in x,
}


def isMatchCondition(current_value, condition):
    return operator_dict[condition.operator](int(current_value), int(condition.value))


def findRoot(groups):
    return list(filter(lambda x: x.head_id == None, groups))[0]


def findChildren(group, groups):
    return list(filter(lambda x: x.head_id == group.id, groups))


def isMatchGroup(customer, current_group, groups, log):
    if current_group.condition:
        current_value = list(
            filter(
                lambda x: x.id == current_group.condition.attribute_id,
                customer.attributes,
            )
        )
        if len(current_value) == 0:
            result = False
            log.append((current_group.id, result, None))
        else:
            current_value = current_value[0].value
            result = isMatchCondition(current_value, current_group.condition)
            log.append((current_group.id, result, current_value))

    else:
        children = findChildren(current_group, groups)
        result = (
            any([isMatchGroup(customer, child, groups, log) for child in children])
            if current_group.logic_id == 5
            else all([isMatchGroup(customer, child, groups, log) for child in children])
        )
        # result = True if current_group.logic_id == 2 else False
        # for child in children:
        #     if (
        #         not isMatchGroup(customer, child, groups, log)
        #         and current_group.logic_id == 2
        #     ):
        #         result = False
        #         break
        #     elif (
        #         isMatchGroup(customer, child, groups, log)
        #         and current_group.logic_id == 5
        #     ):
        #         result = True
        #         break
        log.append((current_group.id, result, None))

    # TODO: Log here
    return result


def isMatchOne(customer, label):
    log = []
    final_logic = isMatchGroup(customer, findRoot(label.groups), label.groups, log)

    # TODO: Log here
    result_id = api.addResult(
        body={
            "customer_id": customer.id,
            "label_id": label.id,
            "logic_value": final_logic,
        }
    )
    for group_id, result, value in log:
        api.addGroupResult(
            {
                "result_id": result_id,
                "group_id": group_id,
                "is_true": result,
                "customer_value": value,
            }
        )
    producer.send(
        "result",
        {
            "customer_id": customer.id,
            "label_id": label.id,
            "is_match": final_logic,
            "result_id": result_id,
        },
    )
    return final_logic


def isMatchMany(customer, labels):
    return {label.id: isMatchOne(customer, label) for label in labels}


@app.get("/", status_code=200)
async def root():
    return {"message": "Hello World"}


@app.get("/one_on_one/{customer_id}/{label_id}", status_code=200)
async def one_on_one(customer_id: int, label_id: int):
    print(customer_id, label_id)
    customer = api.fetchCustomerById(customer_id)
    label = api.fetchLabelById(label_id)
    return {
        "status": "success",
        "is_match": isMatchOne(customer, label),
    }


@app.get("/one_on_many/{customer_id}", status_code=200)
async def one_on_many(customer_id: int):
    print(customer_id)
    customer = api.fetchCustomerById(customer_id)
    labels = api.fetchAllLabels()
    return {
        "status": "success",
        "is_match": isMatchMany(customer, labels),
    }


@app.get("/many_on_one/{label_id}", status_code=200)
async def many_on_one(label_id: int):
    print(label_id)
    customers = api.fetchAllCustomers()
    label = api.fetchLabelById(label_id)
    return {
        "status": "success",
        "is_match": {
            customer.id: isMatchOne(customer, label) for customer in customers
        },
    }


@app.get("/many_on_many", status_code=200)
async def many_on_many():
    print("all")
    customers = api.fetchAllCustomers()
    labels = api.fetchAllLabels()
    return {
        "status": "success",
        "is_match": {
            customer.id: isMatchMany(customer, labels) for customer in customers
        },
    }
