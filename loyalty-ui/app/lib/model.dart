import 'package:flutter/material.dart';

import 'constants.dart';

class ConditionModel {
  ConditionModel({
    required this.conditionId,
    required this.attribute,
    required this.operatorId,
    required this.operatorNotation,
    required this.value,
  });

  final dynamic conditionId;
  final dynamic attribute;
  dynamic operatorId;
  dynamic operatorNotation;
  dynamic valueController;
  dynamic value;

  void setController() {
    valueController = TextEditingController();
  }
}

class Group {
  Group(
      {required this.groupId,
      required this.headId,
      required this.logicId,
      required this.logicNotation,
      required this.condition,
      required this.children,
      this.customerValue = null,
      this.isTrue = true});

  int groupId;
  dynamic headId;
  dynamic logicId;
  dynamic logicNotation;
  ConditionModel condition;
  List<Group> children;
  bool isTrue;
  dynamic customerValue;

  dynamic get conditionAttributeName {
    return condition.attribute.attributeName;
  }

  dynamic get conditionOperatorNotation {
    return condition.operatorNotation;
  }

  dynamic get conditionValue {
    return condition.value;
  }

  dynamic get conditionAttributeId {
    return condition.attribute.attributeId;
  }

  dynamic get conditionAttributeType {
    return condition.attribute.attributeType;
  }

  String get conditionOperatorRepresent {
    return operatorRepresents[conditionOperatorNotation];
  }
}

class Label {
  Label({required this.labelId, required this.labelName, required this.status});
  int labelId;
  String labelName;
  final List<Group> groups = [];
  String status;
  bool isHaveGroup = false;
  dynamic headId;

  void addGroup(Group group) {
    groups.add(group);
  }
}

class Attribute {
  Attribute({
    required this.attributeId,
    required this.attributeName,
    required this.attributeType,
    required this.attributeValue,
  });
  dynamic attributeId;
  final dynamic attributeName;
  final dynamic attributeType;
  final dynamic attributeValue;
}

class Customer {
  const Customer({
    required this.customerId,
    required this.attributes,
  });
  final int customerId;
  final List<Attribute> attributes;
}

class Result {
  Result({
    required this.resultId,
    required this.customerId,
    required this.labelId,
    required this.labelName,
    required this.isTrue,
    required this.datetime,
  });
  late int resultId;
  late int customerId;
  late int labelId;
  late String labelName;
  late bool isTrue;
  late DateTime datetime;
  List<Group> groups = [];
  bool isHaveGroup = false;

}
