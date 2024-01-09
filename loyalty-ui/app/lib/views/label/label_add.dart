import 'package:app/constants.dart';
import 'package:app/model.dart';
import 'package:app/widgets/dialog.dart';
import 'package:flutter/material.dart';
import 'package:flutter_fancy_tree_view/flutter_fancy_tree_view.dart';
import 'package:get/get.dart';
import 'package:app/controller.dart';

class LabelAdd extends StatelessWidget {
  LabelAdd({super.key});
  final DataController dataController = Get.find<DataController>();
  final TextEditingController labelName = TextEditingController();
  final hoverDelete = false.obs;
  @override
  Widget build(BuildContext context) {
    final treeController = TreeController<Group>(
      roots: dataController.newLabelObject.value.groups,
      childrenProvider: (Group node) => node.children,
    );
    treeController.expandAll();
    labelName.text = dataController.newLabelObject.value.labelName;

    return Obx(() {
      if (dataController.attributes.isEmpty) {
        return const Center(
            child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            CircularProgressIndicator(),
            SizedBox(
              height: 20,
            ),
            Text("Đang load các thuộc tính", style: TextStyle(fontSize: 20))
          ],
        ));
      } else {
        return Scaffold(
          backgroundColor: Colors.white,
          appBar: AppBar(
            toolbarHeight: 80,
            backgroundColor: Colors.white,
            title: Row(
              children: [
                Expanded(
                  flex: 1,
                  child: TextField(
                    controller: labelName,
                    onChanged: (value) {
                      dataController.newLabelObject.value.labelName = value;
                    },
                    decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: "Tên cho nhãn mới",
                        floatingLabelBehavior: FloatingLabelBehavior.always),
                  ),
                ),
                const SizedBox(
                  width: 20,
                ),
                Expanded(
                  flex: 1,
                  child: DropdownMenu(
                      menuHeight: 500,
                      initialSelection:
                          dataController.newLabelObject.value.headId != null
                              ? dataController.labelIndexFromId(
                                  dataController.newLabelObject.value.headId)
                              : null,
                      label: const Text("Chọn cha"),
                      onSelected: (value) {
                        dataController.newLabelObject.value.headId = value;
                      },
                      inputDecorationTheme: const InputDecorationTheme(
                          border: OutlineInputBorder(),
                          floatingLabelBehavior: FloatingLabelBehavior.always),
                      dropdownMenuEntries: dataController.labels.map((e) {
                        return DropdownMenuEntry(
                          value: e.labelId,
                          label: e.labelName,
                        );
                      }).toList()),
                ),
              ],
            ),
            leading: BackButton(
              onPressed: () {
                Get.back(id: 1);
              },
            ),
          ),
          body: Padding(
            padding: const EdgeInsets.only(left: 20),
            child: AnimatedTreeView<Group>(
              treeController: treeController,
              nodeBuilder: (BuildContext context, TreeEntry<Group> group) {
                return TreeIndentation(
                  entry: group,
                  guide: IndentGuide.connectingLines(
                      indent: 48, color: Theme.of(context).primaryColor),
                  child: Padding(
                    padding: const EdgeInsets.fromLTRB(10, 20, 20, 20),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.end,
                      children: [
                        Row(
                          children: [
                            Builder(
                              builder: (context) {
                                if (group.node.logicId != null) {
                                  return ElevatedButton(
                                      onPressed: () {
                                        if (group.node.logicId == 2) {
                                          group.node.logicId = 5;
                                          group.node.logicNotation = "or";
                                        } else {
                                          group.node.logicId = 2;
                                          group.node.logicNotation = "and";
                                        }
                                        treeController.expandAll();
                                      },
                                      child: Text(
                                        "${group.node.logicNotation.toUpperCase()}",
                                        style: const TextStyle(fontSize: 20),
                                      ));
                                } else {
                                  return Row(
                                    mainAxisAlignment:
                                        MainAxisAlignment.spaceEvenly,
                                    children: [
                                      // * Attribute
                                      DropdownMenu(
                                          menuHeight: 600,
                                          width: 500,
                                          enableSearch: true,
                                          label: const Text("Thuộc tính"),
                                          initialSelection: 0,
                                          onSelected: (value) {
                                            group.node.condition.attribute
                                                    .attributeId =
                                                dataController
                                                    .attributeAt(value!)
                                                    .attributeId;
                                          },
                                          dropdownMenuEntries: List.generate(
                                              dataController.attributes.length,
                                              (index) => DropdownMenuEntry(
                                                  value: index,
                                                  label: dataController
                                                      .attributes[index]
                                                      .attributeName))),
                                      const SizedBox(
                                        width: 20,
                                      ),
                                      // * Operator
                                      DropdownMenu(
                                          label: const Text("Toán tử"),
                                          initialSelection: 0,
                                          onSelected: (value) {
                                            if (value != null) {
                                              group.node.condition.operatorId =
                                                  1 + value;
                                            }
                                          },
                                          dropdownMenuEntries: List.generate(
                                              dataController.operators.length,
                                              (index) => DropdownMenuEntry(
                                                  value: index,
                                                  label: operatorRepresents[
                                                          dataController
                                                                  .operators[
                                                              index + 1]]
                                                      .toString()))),
                                      const SizedBox(
                                        width: 20,
                                      ),
                                      // * Value
                                      SizedBox(
                                        width: 300,
                                        child: TextField(
                                            onChanged: (value) {
                                              group.node.condition.value =
                                                  value;
                                            },
                                            decoration: const InputDecoration(
                                                border: OutlineInputBorder(),
                                                labelText: "Giá trị",
                                                floatingLabelBehavior:
                                                    FloatingLabelBehavior
                                                        .always)),
                                      ),
                                    ],
                                  );
                                }
                              },
                            ),
                          ],
                        ),
                        Row(
                          children: [
                            group.node.groupId != -1
                                ? IconButton(
                                    tooltip: "Thêm nhóm cùng cấp",
                                    onPressed: () {
                                      group.parent!.node.children.add(
                                          dataController.createNewGroup(
                                              group.parent!.node.groupId,
                                              DateTime.now()
                                                      .millisecondsSinceEpoch %
                                                  10000,
                                              hasCondition: false));
                                      treeController.expandAll();
                                      dataController.update();
                                    },
                                    icon: const Icon(Icons.arrow_downward))
                                : const SizedBox(),
                            group.node.condition.conditionId == null
                                ? IconButton(
                                    tooltip: "Thêm nhóm con",
                                    onPressed: () {
                                      group.node.children.add(
                                          dataController.createNewGroup(
                                              group.node.groupId,
                                              DateTime.now()
                                                      .millisecondsSinceEpoch %
                                                  10000));
                                      treeController.expandAll();
                                      dataController.update();
                                    },
                                    icon: const Icon(
                                      Icons.subdirectory_arrow_right,
                                      color: Colors.blue,
                                    ))
                                : const SizedBox(),
                            group.node.condition.conditionId == null
                                ? IconButton(
                                    tooltip: "Thêm điều kiện",
                                    onPressed: () {
                                      group.node.children.add(
                                        dataController.createNewGroup(
                                            group.node.groupId,
                                            DateTime.now()
                                                    .millisecondsSinceEpoch %
                                                10000,
                                            hasCondition: true),
                                      );
                                      treeController.expandAll();
                                      dataController.update();
                                    },
                                    icon: const Icon(Icons.add,
                                        color: Colors.green))
                                : const SizedBox(),
                            MouseRegion(
                                onEnter: (event) {
                                  hoverDelete.value = true;
                                },
                                onExit: (event) {
                                  hoverDelete.value = false;
                                },
                                child: IconButton(
                                    tooltip: (!hoverDelete.value &&
                                            group.node.groupId !=
                                                dataController.newLabelObject
                                                    .value.groups[0].groupId)
                                        ? "Xóa nhóm"
                                        : "Ai cho xóa",
                                    onPressed: () {
                                      group.parent!.node.children
                                          .remove(group.node);
                                      treeController.expandAll();
                                      dataController.update();
                                    },
                                    icon: const Icon(
                                      Icons.remove,
                                      color: Colors.red,
                                    )))
                          ],
                        )
                      ],
                    ),
                  ),
                );
              },
            ),
          ),
          floatingActionButton: FloatingActionButton.extended(
              onPressed: () {
                Get.dialog(createDialog(
                    context,
                    "Xác nhận tạo nhãn ${dataController.newLabelObject.value.labelName}",
                    "Bạn có chắc muốn tạo nhãn này không?", () async {
                  dataController.saveNewLabel().then((code) {
                    if (code == 201) {
                      Get.snackbar("Thành công", "Tạo nhãn thành công",
                          colorText: Colors.green,
                          snackPosition: SnackPosition.BOTTOM);
                    } else if (code == 409) {
                      Get.snackbar("Thất bại", "Tên nhãn đã tồn tại",
                          colorText: Colors.red,
                          snackPosition: SnackPosition.BOTTOM);
                    } else {
                      Get.snackbar("Thất bại", "Lỗi $code",
                          colorText: Colors.red,
                          snackPosition: SnackPosition.BOTTOM);
                    }
                  });
                  Get.back();
                }, () {
                  Get.back();
                }, "Tạo", "Hủy"));
              },
              label: const Text("Lưu"),
              icon: const Icon(Icons.save)),
        );
      }
    });
  }
}
