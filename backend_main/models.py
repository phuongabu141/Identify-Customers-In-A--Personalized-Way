import typing
import pydantic
import typing
from pydantic import BaseModel


class Condition(BaseModel):
    condition_id: int
    attribute_id: int
    operator: int
    value: typing.Any


class Group(BaseModel):
    id: int
    head_id: typing.Optional[int]
    logic_id: typing.Optional[int]
    condition: typing.Optional[Condition] = None


class Label(BaseModel):
    id: int
    name: str
    groups: typing.List[Group]


class Attribute(BaseModel):
    id: int
    dtype: typing.Type
    value: typing.Any

    def __repr__(self):
        return f"{self.id} = {self.value}"


class Customer(BaseModel):
    id: int
    attributes: typing.List[Attribute] = []


