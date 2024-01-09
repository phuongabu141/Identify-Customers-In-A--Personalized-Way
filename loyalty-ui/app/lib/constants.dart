const List<Map> mockGroups = [
  {
    "group_id": 9,
    "head_group_id": null,
    "logicModel": {
      "logic_id": 5,
      "logic_name": "toán tử or",
      "description": "toán tử hoặc để kết hợp nhiều điều kiện",
      "notation": "or",
      "created_time": "2023-03-19T17:00:00.000+00:00",
      "created_by": 155
    },
    "created_time": null,
    "conditionModel": null,
    "labelModel": {
      "label_id": 2,
      "label_name": "Trung thành bia Heineken hạng bạc",
      "status": "Đã kích hoạt",
      "created_time": "2023-12-08T17:00:00.000+00:00",
      "created_by": null,
      "description": ""
    },
    "_delete": false
  },
  {
    "group_id": 3,
    "head_group_id": 5,
    "logicModel": null,
    "created_time": null,
    "conditionModel": {
      "condition_id": 3,
      "attributeModel": {
        "attribute_id": 4,
        "name": "Số lần mua bia Heineken",
        "description":
            "Tổng số lần mua bia Heineken của một khách hàng trong khoảng thời gian",
        "attribute_type": "attribute",
        "created_time": "2023-12-08T17:00:00.000+00:00",
        "created_by": null,
        "start_date": "2023-01-01",
        "end_date": "2023-04-01",
        "_delete": false
      },
      "operatorModel": {
        "operator_id": 4,
        "operator_name": "lớn hơn hoặc bằng",
        "description": "toán từ lớn hơn hoặc bằng",
        "notation": ">=",
        "created_time": "2022-12-29T17:00:00.000+00:00",
        "created_by": null,
        "_delete": false
      },
      "value": "3",
      "created_time": null,
      "created_by": null,
      "_delete": false
    },
    "labelModel": {
      "label_id": 2,
      "label_name": "Trung thành bia Heineken hạng bạc",
      "status": "Đã kích hoạt",
      "created_time": "2023-12-08T17:00:00.000+00:00",
      "created_by": null,
      "description": ""
    },
    "_delete": false
  },
  {
    "group_id": 4,
    "head_group_id": 5,
    "logicModel": null,
    "created_time": null,
    "conditionModel": {
      "condition_id": 4,
      "attributeModel": {
        "attribute_id": 4,
        "name": "Số lần mua bia Heineken",
        "description":
            "Tổng số lần mua bia Heineken của một khách hàng trong khoảng thời gian",
        "attribute_type": "attribute",
        "created_time": "2023-12-08T17:00:00.000+00:00",
        "created_by": null,
        "start_date": "2023-01-01",
        "end_date": "2023-04-01",
        "_delete": false
      },
      "operatorModel": {
        "operator_id": 5,
        "operator_name": "nhỏ hơn hoặc bằng",
        "description": "toán tử nhỏ hơn hoặc bằng ",
        "notation": "<=",
        "created_time": "2022-12-29T17:00:00.000+00:00",
        "created_by": null,
        "_delete": false
      },
      "value": "10",
      "created_time": null,
      "created_by": null,
      "_delete": false
    },
    "labelModel": {
      "label_id": 2,
      "label_name": "Trung thành bia Heineken hạng bạc",
      "status": "Đã kích hoạt",
      "created_time": "2023-12-08T17:00:00.000+00:00",
      "created_by": null,
      "description": ""
    },
    "_delete": false
  },
  {
    "group_id": 5,
    "head_group_id": 9,
    "logicModel": {
      "logic_id": 2,
      "logic_name": "toán tử and",
      "description": "toán tử và để kết hợp nhiều điều kiện",
      "notation": "and",
      "created_time": "2023-03-19T17:00:00.000+00:00",
      "created_by": 155
    },
    "created_time": null,
    "conditionModel": null,
    "labelModel": {
      "label_id": 2,
      "label_name": "Trung thành bia Heineken hạng bạc",
      "status": "Đã kích hoạt",
      "created_time": "2023-12-08T17:00:00.000+00:00",
      "created_by": null,
      "description": ""
    },
    "_delete": false
  },
  {
    "group_id": 6,
    "head_group_id": 8,
    "logicModel": null,
    "created_time": null,
    "conditionModel": {
      "condition_id": 48,
      "attributeModel": {
        "attribute_id": 13,
        "name": "Số lượng mua bia Heineken",
        "description":
            "Tổng số lượng lon bia Heniken của một khách hàng trong một khoảng thời gian",
        "attribute_type": "attribute",
        "created_time": "2023-12-08T17:00:00.000+00:00",
        "created_by": null,
        "start_date": "2023-01-01",
        "end_date": "2023-04-02",
        "_delete": false
      },
      "operatorModel": {
        "operator_id": 4,
        "operator_name": "lớn hơn hoặc bằng",
        "description": "toán từ lớn hơn hoặc bằng",
        "notation": ">=",
        "created_time": "2022-12-29T17:00:00.000+00:00",
        "created_by": null,
        "_delete": false
      },
      "value": "10",
      "created_time": null,
      "created_by": null,
      "_delete": false
    },
    "labelModel": {
      "label_id": 2,
      "label_name": "Trung thành bia Heineken hạng bạc",
      "status": "Đã kích hoạt",
      "created_time": "2023-12-08T17:00:00.000+00:00",
      "created_by": null,
      "description": ""
    },
    "_delete": false
  },
  {
    "group_id": 7,
    "head_group_id": 8,
    "logicModel": null,
    "created_time": null,
    "conditionModel": {
      "condition_id": 49,
      "attributeModel": {
        "attribute_id": 13,
        "name": "Số lượng mua bia Heineken",
        "description":
            "Tổng số lượng lon bia Heniken của một khách hàng trong một khoảng thời gian",
        "attribute_type": "attribute",
        "created_time": "2023-12-08T17:00:00.000+00:00",
        "created_by": null,
        "start_date": "2023-01-01",
        "end_date": "2023-04-02",
        "_delete": false
      },
      "operatorModel": {
        "operator_id": 5,
        "operator_name": "nhỏ hơn hoặc bằng",
        "description": "toán tử nhỏ hơn hoặc bằng ",
        "notation": "<=",
        "created_time": "2022-12-29T17:00:00.000+00:00",
        "created_by": null,
        "_delete": false
      },
      "value": "24",
      "created_time": null,
      "created_by": null,
      "_delete": false
    },
    "labelModel": {
      "label_id": 2,
      "label_name": "Trung thành bia Heineken hạng bạc",
      "status": "Đã kích hoạt",
      "created_time": "2023-12-08T17:00:00.000+00:00",
      "created_by": null,
      "description": ""
    },
    "_delete": false
  },
  {
    "group_id": 8,
    "head_group_id": 9,
    "logicModel": {
      "logic_id": 2,
      "logic_name": "toán tử and",
      "description": "toán tử và để kết hợp nhiều điều kiện",
      "notation": "and",
      "created_time": "2023-03-19T17:00:00.000+00:00",
      "created_by": 155
    },
    "created_time": null,
    "conditionModel": null,
    "labelModel": {
      "label_id": 2,
      "label_name": "Trung thành bia Heineken hạng bạc",
      "status": "Đã kích hoạt",
      "created_time": "2023-12-08T17:00:00.000+00:00",
      "created_by": null,
      "description": ""
    },
    "_delete": false
  }
];

const List<Map<String, dynamic>> mockLabels = [
  {"label_id": 7, "label_name": "Trung thành bia Tiger hạng kim cương"},
  {"label_id": 2, "label_name": "Trung thành bia Heineken hạng bạc"},
  {"label_id": 3, "label_name": "Trung thành bia Heineken hạng vàng"},
  {"label_id": 8, "label_name": "Trung thành bia Larue hạng bạc"},
  {"label_id": 6, "label_name": "Trung thành bia Tiger hạng vàng"},
  {"label_id": 9, "label_name": "Trung thành bia Larue hạng vàng"},
  {"label_id": 4, "label_name": "Trung thành bia Heineken hạng kim cương"},
  {"label_id": 10, "label_name": "Trung thành bia Larue hạng kim cương"},
  {"label_id": 5, "label_name": "Trung thành bia Tiger hạng bạc"},
  {"label_id": 7, "label_name": "Trung thành bia Tiger hạng kim cương"},
  {"label_id": 2, "label_name": "Trung thành bia Heineken hạng bạc"},
  {"label_id": 3, "label_name": "Trung thành bia Heineken hạng vàng"},
  {"label_id": 8, "label_name": "Trung thành bia Larue hạng bạc"},
  {"label_id": 6, "label_name": "Trung thành bia Tiger hạng vàng"},
  {"label_id": 9, "label_name": "Trung thành bia Larue hạng vàng"},
  {"label_id": 4, "label_name": "Trung thành bia Heineken hạng kim cương"},
  {"label_id": 10, "label_name": "Trung thành bia Larue hạng kim cương"},
  {"label_id": 5, "label_name": "Trung thành bia Tiger hạng bạc"},
  {"label_id": 7, "label_name": "Trung thành bia Tiger hạng kim cương"},
  {"label_id": 2, "label_name": "Trung thành bia Heineken hạng bạc"},
  {"label_id": 3, "label_name": "Trung thành bia Heineken hạng vàng"},
  {"label_id": 8, "label_name": "Trung thành bia Larue hạng bạc"},
  {"label_id": 6, "label_name": "Trung thành bia Tiger hạng vàng"},
  {"label_id": 9, "label_name": "Trung thành bia Larue hạng vàng"},
  {"label_id": 4, "label_name": "Trung thành bia Heineken hạng kim cương"},
  {"label_id": 10, "label_name": "Trung thành bia Larue hạng kim cương"},
  {"label_id": 5, "label_name": "Trung thành bia Tiger hạng bạc"},
  {"label_id": 7, "label_name": "Trung thành bia Tiger hạng kim cương"},
  {"label_id": 2, "label_name": "Trung thành bia Heineken hạng bạc"},
  {"label_id": 3, "label_name": "Trung thành bia Heineken hạng vàng"},
  {"label_id": 8, "label_name": "Trung thành bia Larue hạng bạc"},
  {"label_id": 6, "label_name": "Trung thành bia Tiger hạng vàng"},
  {"label_id": 9, "label_name": "Trung thành bia Larue hạng vàng"},
  {"label_id": 4, "label_name": "Trung thành bia Heineken hạng kim cương"},
  {"label_id": 10, "label_name": "Trung thành bia Larue hạng kim cương"},
  {"label_id": 5, "label_name": "Trung thành bia Tiger hạng bạc"},
  {"label_id": 7, "label_name": "Trung thành bia Tiger hạng kim cương"},
  {"label_id": 2, "label_name": "Trung thành bia Heineken hạng bạc"},
  {"label_id": 3, "label_name": "Trung thành bia Heineken hạng vàng"},
  {"label_id": 8, "label_name": "Trung thành bia Larue hạng bạc"},
  {"label_id": 6, "label_name": "Trung thành bia Tiger hạng vàng"},
  {"label_id": 9, "label_name": "Trung thành bia Larue hạng vàng"},
  {"label_id": 4, "label_name": "Trung thành bia Heineken hạng kim cương"},
  {"label_id": 10, "label_name": "Trung thành bia Larue hạng kim cương"},
  {"label_id": 5, "label_name": "Trung thành bia Tiger hạng bạc"}
];

const Map operatorRepresents = {
  "<": "Nhỏ hơn",
  ">": "Lớn hơn",
  "==": "Bằng",
  "!=": "Khác",
  ">=": "Lớn hơn hoặc bằng",
  "<=": "Nhỏ hơn hoặc bằng",
  "in": "Trong",
  "not in": "Không trong"
};

List<String> mockAttributes = List.generate(10, (index) => "Attribute $index");
const Map<int, String> mockOperators = {
  1: ">",
  2: "<",
  3: "==",
  4: ">=",
  5: "<=",
  6: "!=",
  7: "in",
  8: "not in",
};

const List<Map<String, dynamic>> edges = [
  {"label_id": 2, "head_id": 29},
  {"label_id": 3, "head_id": 29},
  {"label_id": 4, "head_id": 29},
  {"label_id": 5, "head_id": 30},
  {"label_id": 6, "head_id": 30},
  {"label_id": 7, "head_id": 30},
  {"label_id": 8, "head_id": 31},
  {"label_id": 9, "head_id": 31},
  {"label_id": 10, "head_id": 31},
  {"label_id": 26, "head_id": 37},
  {"label_id": 27, "head_id": 37},
  {"label_id": 28, "head_id": 37},
  {"label_id": 29, "head_id": 85},
  {"label_id": 30, "head_id": 85},
  {"label_id": 31, "head_id": 85},
  {"label_id": 37, "head_id": 85},
  {"label_id": 45, "head_id": 85},
  {"label_id": 70, "head_id": 45},
  {"label_id": 71, "head_id": 45},
  {"label_id": 72, "head_id": 45},
  {"label_id": 83, "head_id": 82},
  {"label_id": 84, "head_id": 82},
  {"label_id": 85, "head_id": 82},
  {"label_id": 86, "head_id": 82},
  {"label_id": 87, "head_id": 82},
  {"label_id": 88, "head_id": 82},
  {"label_id": 89, "head_id": 82},
  {"label_id": 90, "head_id": 82}
];
