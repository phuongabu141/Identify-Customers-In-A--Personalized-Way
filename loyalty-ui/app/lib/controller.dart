import 'dart:convert';
import 'package:app/constants.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;
import 'model.dart';
import 'package:graphview/GraphView.dart' as gv;

class ViewController extends GetxController {
  var currentPageId = 0.obs;

  void moveToPage(int pageId) {
    currentPageId.value = pageId;
  }

  int get currentPage {
    return currentPageId.value;
  }
}

class DataController extends GetxController {
  DataController({required this.hostName, required this.ruleHostName});
  final String hostName;
  final String ruleHostName;

  // State
  RxString ruleStatus = "Khong".obs;
  final RxInt _forcusedLabelIndex = 0.obs;
  final RxInt _forcusedResultIndex = 0.obs;
  RxMap<String, dynamic> loadStatus = {
    "labels": false,
    "attributes": false,
    "groups": false,
    "edges": false,
    "operators": false
  }.obs;

  // Data
  RxObjectMixin newLabelObject = null.obs;
  RxList<Label> labels = <Label>[].obs;
  RxList<Label> originalLabels = <Label>[].obs;
  RxList<Result> results = <Result>[].obs;
  RxList<Attribute> attributes = <Attribute>[].obs;
  RxMap<int, String> operators = {0: "null"}.obs;
  RxObjectMixin graph = gv.Graph().obs;

  bool isLoaded() {
    for (var key in loadStatus.keys) {
      if (loadStatus[key] == false && key != "groups") {
        return false;
      }
    }
    return true;
  }

  @override
  void onInit() {
    super.onInit();
    newLabelObject = createNewLabel().obs;
    fetchAttributes();
    fetchAllLabels();
    fetchOperators();
    fetchGraph();
  }

  void refetch() async {
    super.refresh();
    newLabelObject = createNewLabel().obs;
    fetchAttributes();
    fetchAllLabels();
    fetchOperators();
    fetchGraph();
  }

  fetchGraph() async {
    loadStatus["edges"] = false;

    var uri = Uri.https(hostName, 'api/label-edge/all');
    Map<String, String> headers = {
      "ngrok-skip-browser-warning": "true",
      "Access-Control-Allow-Origin": "*",
      'Content-Type': 'application/json',
      'Accept': '*/*'
    };
    var response = await http.get(uri, headers: headers);
    if (response.statusCode != 200) {
      return;
    }

    graph.value = gv.Graph()..isTree = true;
    for (Map<String, dynamic> edge
        in json.decode(utf8.decode(response.bodyBytes))["data"]) {
      graph.value.addEdge(
        gv.Node.Id(edge["head_id"]),
        gv.Node.Id(edge["label_id"]),
      );
    }
    graph.refresh();
    loadStatus["edges"] = true;
    Future.delayed(const Duration(seconds: 1), () {
      loadStatus.refresh();
    });
  }

  int labelIndexFromId(int id) {
    for (int i = 0; i < labels.length; i++) {
      if (labels[i].labelId == id) {
        return i;
      }
    }
    return -1;
  }

  fetchAttributes() async {
    loadStatus["attributes"] = false;
    var uri = Uri.https(hostName, 'api/attribute/all');
    Map<String, String> headers = {
      "ngrok-skip-browser-warning": "true",
      "Access-Control-Allow-Origin": "*",
      'Content-Type': 'application/json',
      'Accept': '*/*'
    };
    var response = await http.get(uri, headers: headers);
    if (response.statusCode != 200) {
      return;
    }
    List<dynamic> data = json.decode(utf8.decode(response.bodyBytes))["data"];
    attributes.value = data
        .map((e) => Attribute(
            attributeId: e["attribute_id"],
            attributeName: e["attribute_name"].toString(),
            attributeType: int,
            attributeValue: null))
        .toList();
    loadStatus["attributes"] = true;
    Future.delayed(const Duration(seconds: 1), () {
      loadStatus.refresh();
    });
  }

  fetchOperators() async {
    loadStatus["operators"] = false;
    operators.value = mockOperators;
    loadStatus["operators"] = true;
    Future.delayed(const Duration(seconds: 1), () {
      loadStatus.refresh();
    });
  }

  set forcusedLabelIndex(int index) {
    _forcusedLabelIndex.value = index;
  }

  set forcusedResultIndex(int index) {
    _forcusedResultIndex.value = index;
  }

  Result get forcusedResult {
    return results[_forcusedResultIndex.value];
  }

  int get forcusedResultIndex {
    return _forcusedResultIndex.value;
  }

  Label get forcusedLabel {
    return labels[_forcusedLabelIndex.value];
  }

  int get forcusedLabelIndex {
    return _forcusedLabelIndex.value;
  }

  int get numberOfLabels {
    return labels.length;
  }

  Label labelAt(int index) {
    return labels[index];
  }

  Attribute attributeAt(int index) {
    return attributes[index];
  }

  Group createNewGroup(
    dynamic headId,
    int groupId, {
    bool hasCondition = false,
  }) {
    var newGroup = Group(
        groupId: groupId,
        headId: headId,
        logicId: hasCondition ? null : 2,
        logicNotation: hasCondition ? null : "and",
        condition: ConditionModel(
            conditionId: hasCondition
                ? DateTime.now().millisecondsSinceEpoch % 10000
                : null,
            attribute: Attribute(
                attributeId: hasCondition ? attributes[0].attributeId : null,
                attributeName:
                    hasCondition ? attributes[0].attributeName : null,
                attributeType: null,
                attributeValue: null),
            operatorId: hasCondition ? 1 : null,
            operatorNotation: hasCondition ? ">" : null,
            value: hasCondition ? 0 : null),
        children: []);
    newGroup.condition.setController();
    return newGroup;
  }

  Label createNewLabel() {
    var currentId = DateTime.now().millisecondsSinceEpoch % 10000;
    var newLabel = Label(
        labelId: -1, labelName: "Tên nhãn mới $currentId", status: "Nháp");
    newLabel.addGroup(createNewGroup(null, currentId));
    return newLabel;
  }

  String labelNameById(int labelId) {
    for (var label in labels) {
      if (label.labelId == labelId) {
        return label.labelName;
      }
    }
    return "Unknown";
  }

  fetchGroupsByLabelId(int labelId) async {
    var url = Uri.https(hostName, 'api/group/byLabel/$labelId');
    Map<String, String> headers = {
      "ngrok-skip-browser-warning": "true",
      "Access-Control-Allow-Origin": "*",
      'Content-Type': 'application/json',
      'Accept': '*/*'
    };
    var response = await http.get(url, headers: headers);
    if (response.statusCode != 200) {
      return createNewGroup(null, -1);
    }
    var data = json.decode(utf8.decode(response.bodyBytes))["data"];
    List<Group> listGroups = [];
    for (var e in data) {
      listGroups.add(Group(
          groupId: e["group_id"],
          headId: e["head_group_id"],
          logicId: e["logicResponse"]["logic_id"],
          logicNotation: e["logicResponse"]["logic_notation"],
          condition: ConditionModel(
              conditionId: e["conditionResponse"]["condition_id"],
              attribute: Attribute(
                  attributeId: e["conditionResponse"]["attributeResponse"]
                      ["attribute_id"],
                  attributeName: e["conditionResponse"]["attributeResponse"]
                      ["attribute_name"],
                  attributeValue: null,
                  attributeType: null),
              operatorId: e["conditionResponse"]["operatorResponse"]
                  ["operator_id"],
              operatorNotation: e["conditionResponse"]["operatorResponse"]
                  ["notation"],
              value: e["conditionResponse"]["value"]),
          children: []));
    }
    for (var group in listGroups) {
      for (var parentGroup in listGroups) {
        if (parentGroup.groupId == group.headId) {
          parentGroup.children.add(group);
        }
      }
    }
    if (listGroups.isNotEmpty) {
      labels[forcusedLabelIndex].groups.clear();
      labels[forcusedLabelIndex]
          .groups
          .add(listGroups.where((e) => e.headId == null).toList().first);
    }
    labels[forcusedLabelIndex].isHaveGroup = true;
    labels.refresh();
  }

  fetchAllLabels() async {
    loadStatus["labels"] = false;
    List<Label> tempList = [];
    var url = Uri.https(hostName, 'api/label/all');
    Map<String, String> headers = {
      "ngrok-skip-browser-warning": "true",
      "Access-Control-Allow-Origin": "*",
      'Content-Type': 'application/json',
      'Accept': '*/*'
    };
    var response = await http.get(url, headers: headers);

    if (response.statusCode != 200) {
      return;
    }

    var data = json.decode(utf8.decode(response.bodyBytes))["data"];

    for (var label in data) {
      var tempLabel = Label(
          labelId: label["label_id"],
          labelName: label["label_name"],
          status: label["status"]);
      tempList.add(tempLabel);
    }
    tempList.sort((a, b) => a.labelId.compareTo(b.labelId));
    labels.value = tempList;
    originalLabels.value = tempList;
    loadStatus["labels"] = true;
    Future.delayed(const Duration(seconds: 1), () {
      loadStatus.refresh();
    });
  }

  List<Group> treeToListGroup(Group root) {
    List<Group> list = [];
    list.add(root);
    for (var child in root.children) {
      list.addAll(treeToListGroup(child));
    }
    return list;
  }

  saveNewGroup(Group g) async {
    var url = Uri.https(hostName, 'api/group/add');
    Map<String, String> headers = {
      'Content-Type': "application/json",
    };

    Map body = {
      "group_id": g.groupId,
      "head_group_id": g.headId,
      "label_id": newLabelObject.value.labelId,
      "logic_id": g.logicId,
      "conditionRequest": {
        "attribute_id": g.condition.attribute.attributeId,
        "operator_id": g.condition.operatorId,
        "value": g.condition.value
      }
    };

    var response = await http.post(url,
        headers: headers, body: utf8.encode(json.encode(body)));

    return response.statusCode;
  }

  Future<int> saveNewLabel() async {
    var url = Uri.https(hostName, 'api/label/add');
    Map<String, String> headers = {
      'Content-Type': "application/json",
    };
    Map body = {
      "label_name": newLabelObject.value.labelName,
      "head_label_id": newLabelObject.value.headId,
      "description": ""
    };

    var response = await http.post(url,
        headers: headers, body: utf8.encode(json.encode(body)));

    if (response.statusCode == 201) {
      newLabelObject.value.labelId =
          json.decode(response.body)["data"]["label_id"];
      var data = treeToListGroup(newLabelObject.value.groups[0]);
      for (Group g in data) {
        var code = await saveNewGroup(g);
        if (code != 201) {
          return code;
        }
      }
    }
    return response.statusCode;
  }

  fetchResultDetail(int resultId) async {
    var url = Uri.https(hostName, 'api/ResultGroup/ByResultId/$resultId');
    Map<String, String> headers = {
      "ngrok-skip-browser-warning": "true",
      "Access-Control-Allow-Origin": "*",
      'Content-Type': 'application/json',
      'Accept': '*/*'
    };
    var response = await http.get(url, headers: headers);

    if (response.statusCode != 200) {
      return;
    }

    var data = json.decode(utf8.decode(response.bodyBytes))["data"];

    List<Group> listGroups = [];
    for (var res in data) {
      var e = res["groupResponse"];
      var tempGroup = Group(
          groupId: e["group_id"],
          customerValue: res["customer_value"],
          isTrue: res["logic_value"],
          headId: e["head_group_id"],
          logicId: e["logicResponse"]["logic_id"],
          logicNotation: e["logicResponse"]["logic_notation"],
          condition: ConditionModel(
              conditionId: e["conditionResponse"]["condition_id"],
              attribute: Attribute(
                  attributeId: e["conditionResponse"]["attributeResponse"]
                      ["attribute_id"],
                  attributeName: e["conditionResponse"]["attributeResponse"]
                      ["attribute_name"],
                  attributeValue: null,
                  attributeType: null),
              operatorId: e["conditionResponse"]["operatorResponse"]
                  ["operator_id"],
              operatorNotation: e["conditionResponse"]["operatorResponse"]
                  ["notation"],
              value: e["conditionResponse"]["value"]),
          children: []);
      listGroups.add(tempGroup);
    }

    for (var group in listGroups) {
      for (var parentGroup in listGroups) {
        if (parentGroup.groupId == group.headId) {
          parentGroup.children.add(group);
        }
      }
    }
    if (listGroups.isNotEmpty) {
      results[forcusedResultIndex].groups.clear();
      results[forcusedResultIndex]
          .groups
          .add(listGroups.where((e) => e.headId == null).toList().first);
    }

    results[forcusedResultIndex].isHaveGroup = true;
    results.refresh();
  }

  fetchALlResult() async {
    loadStatus["labels"] = false;
    var url = Uri.https(hostName, 'api/result/all');
    Map<String, String> headers = {
      "ngrok-skip-browser-warning": "true",
      "Access-Control-Allow-Origin": "*",
      'Content-Type': 'application/json',
      'Accept': '*/*'
    };
    var response = await http.get(url, headers: headers);

    if (response.statusCode != 200) {
      return;
    }
    List<Result> tempList = [];
    var data = json.decode(utf8.decode(response.bodyBytes))["data"];
    for (var res in data) {
      tempList.add(
        Result(
          resultId: res["result_id"],
          labelId: res["labelResponse"]["label_id"],
          customerId: res["customerResponse"]["customer_id"],
          labelName: res["labelResponse"]["label_name"],
          isTrue: res["logic_value"],
          datetime: DateTime.parse(res["time"]),
        ),
      );
    }
    tempList.sort((a, b) => b.datetime.compareTo(a.datetime));
    results.value = tempList;
  }

  activateLabel(int labelId) async {
    var url = Uri.https(hostName, 'api/label/activeLabel/$labelId');
    Map<String, String> headers = {
      'Content-Type': "application/json",
    };
    var response = await http.put(url, headers: headers);
    if (response.statusCode == 200) {
      var data = json.decode(response.body)["data"];
      labels[forcusedLabelIndex].status = data["status"];
      runRuleOneOnMany(labelId);
      labels.refresh();
    }
  }

  runRuleOneOnMany(int labelId) async {
    ruleStatus.value = "Dang";
    var url = Uri.http(ruleHostName, '/many_on_one/$labelId');
    Map<String, String> headers = {
      "ngrok-skip-browser-warning": "true",
      "Access-Control-Allow-Origin": "*",
      'Content-Type': 'application/json',
      'Accept': '*/*'
    };
    var response = await http.get(url, headers: headers);
    if (response.statusCode == 200) {
      var data = json.decode(utf8.decode(response.bodyBytes));
      if (data["status"] == "success") {
        ruleStatus.value = "Thanh cong";
      } else {
        ruleStatus.value = "That bai";
      }
    }
  }
}
