from concurrent.futures import ThreadPoolExecutor
from models import *
import requests
import typing
import json


class Api:
    def __init__(self, host: str) -> None:
        self.hostname = host
        self.session = requests.Session()

    def fetchAllCustomers(self) -> typing.List[Customer]:
        url = f"{self.hostname}/api/customer_attribute/allCustomerAttribute"

        payload = {}
        headers = {}

        response = requests.request("GET", url, headers=headers, data=payload)
        data = json.loads(response.text)["data"]

        attr = {}
        for customer in data:
            if customer["customer_id"] not in attr:
                attr[customer["customer_id"]] = []
            else:
                attr[customer["customer_id"]].append(
                    Attribute(
                        id=customer["attribute_id"],
                        dtype=int,
                        value=customer["value"],
                    )
                )

        return [Customer(id=cus, attributes=attr[cus]) for cus in attr]

    def fetchCustomerById(self, customer_id: int) -> typing.List[Customer]:
        url = f"{self.hostname}/api/customer_attribute/ByCustomerId/{customer_id}"

        payload = {}
        headers = {}

        response = requests.request("GET", url, headers=headers, data=payload)
        data = json.loads(response.text)

        attr = {}
        for customer in data:
            if customer["customer_id"] not in attr:
                attr[customer["customer_id"]] = []
            else:
                attr[customer["customer_id"]].append(
                    Attribute(
                        id=customer["attribute_id"],
                        dtype=int,
                        value=customer["value"],
                    )
                )

        return [Customer(id=cus, attributes=attr[cus]) for cus in attr][0]

    def fetchGroupsByLabelId(self, label_id) -> typing.Any:
        url = f"{self.hostname}/api/group/byLabel/{label_id}"

        payload = {}
        headers = {}

        response = requests.request("GET", url, headers=headers, data=payload)
        data = json.loads(response.text)["data"]

        for group in data:
            yield Group(
                id=group["group_id"],
                head_id=group["head_group_id"],
                logic_id=group["logicResponse"]["logic_id"],
                condition=Condition(
                    condition_id=group["conditionResponse"]["condition_id"],
                    attribute_id=group["conditionResponse"]["attributeResponse"][
                        "attribute_id"
                    ],
                    operator=group["conditionResponse"]["operatorResponse"][
                        "operator_id"
                    ],
                    value=group["conditionResponse"]["value"].replace(",", ""),
                )
                if group["conditionResponse"]["condition_id"]
                else None,
            )

    def fetchAllLabels(self):
        url = f"{self.hostname}/api/label/LabelMapGroup"

        payload = {}
        headers = {}

        response = requests.request("GET", url, headers=headers, data=payload)
        data = json.loads(response.text)["data"]
        executor = ThreadPoolExecutor(max_workers=10)
        groups = list(
            executor.map(
                self.fetchGroupsByLabelId, [label["label_id"] for label in data]
            )
        )
        return [
            Label(
                id=label["label_id"],
                name=label["label_name"],
                groups=list(groups[i]),
            )
            for i, label in enumerate(data)
        ]

    def fetchLabelById(self, label_id: int):
        url = f"{self.hostname}/api/label/{label_id}"

        payload = {}
        headers = {}

        response = requests.request("GET", url, headers=headers, data=payload)
        label = json.loads(response.text)["data"]
        return Label(
            id=label["label_id"],
            name=label["label_name"],
            groups=list(self.fetchGroupsByLabelId(label["label_id"])),
        )

    def addGroupResult(self, body: dict):
        url = f"{self.hostname}/api/ResultGroup/add"

        payload = json.dumps(body).encode("utf-8")

        headers = {"Content-Type": "application/json"}

        response = requests.request("POST", url, headers=headers, data=payload)

    def addResult(self, body: dict):
        url = f"{self.hostname}/api/result/add"

        payload = json.dumps(body).encode("utf-8")
        header = {"Content-Type": "application/json"}

        response = requests.request("POST", url, headers=header, data=payload)

        return json.loads(response.text)["data"]["result_id"]

    def fetchCurrentLabels(self, customer_id: int):
        url = f"{self.hostname}/api/CustomerLabelEvent/lastEventOfCustomerId/{customer_id}"

        payload = {}
        headers = {}

        response = requests.request("GET", url, headers=headers, data=payload)
        data = json.loads(response.text)["data"]
        return [label["label_id"] for label in data if label["event_type_id"] == 1]

    def addEvent(self, body: dict):
        url = f"{self.hostname}/api/CustomerLabelEvent/add"

        payload = json.dumps(body)
        headers = {"Content-Type": "application/json"}

        response = requests.request("POST", url, headers=headers, data=payload)


        payload = json.dumps(body).encode("utf-8")
        header = {"Content-Type": "application/json"}
        
        return json.loads(response.text)["data"]["customer_label_event_id"]

    def addCustomerLabel(self, body: dict):
        url = f"{self.hostname}/api/CustomerLabel/add"

        payload = json.dumps(body).encode("utf-8")
        header = {"Content-Type": "application/json"}

        response = requests.request("POST", url, headers=header, data=payload)
