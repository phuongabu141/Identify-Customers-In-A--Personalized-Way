import 'package:app/constants.dart';
import 'package:app/model.dart';
import 'package:flutter/material.dart';
import 'package:flutter_fancy_tree_view/flutter_fancy_tree_view.dart';
import 'package:get/get.dart';
import 'package:app/controller.dart';
import 'package:app/widgets/all.dart';

class LabelDetail extends StatelessWidget {
  LabelDetail({super.key});
  final DataController dataController = Get.find<DataController>();
  @override
  Widget build(BuildContext context) {
    dataController.fetchGroupsByLabelId(dataController.forcusedLabel.labelId);
    final treeController = TreeController<Group>(
      roots: dataController.forcusedLabel.groups,
      childrenProvider: (Group node) => node.children,
    );
    treeController.expandAll();
    return Scaffold(
        appBar: AppBar(
          title: Text(dataController.forcusedLabel.labelName),
          leading: BackButton(
            onPressed: () {
              Get.back(id: 1);
            },
          ),
        ),
        body: Obx(() {
          if (!dataController.forcusedLabel.isHaveGroup) {
            return const Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  CircularProgressIndicator(),
                  SizedBox(
                    height: 20,
                  ),
                  Text(
                    "Đang tải dữ liệu Group",
                    style: TextStyle(fontSize: 20, color: Colors.red),
                  )
                ],
              ),
            );
          } else {
            if (dataController.forcusedLabel.groups.isEmpty) {
              return const Center(
                child: Text(
                  "Không có Group nào cả, Thêm Group đi",
                  style: TextStyle(fontSize: 20),
                ),
              );
            } else {
              treeController.expandAll();
              return Padding(
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
                        child: Row(
                          children: [
                            Builder(
                              builder: (context) {
                                if (group.node.logicId != null) {
                                  return ElevatedButton(
                                      onPressed: () {
                                        treeController
                                            .toggleExpansion(group.node);
                                      },
                                      child: Text(
                                          "${group.node.logicNotation.toUpperCase()}",
                                          style:
                                              const TextStyle(fontSize: 20)));
                                } else {
                                  return Row(
                                    mainAxisAlignment:
                                        MainAxisAlignment.spaceEvenly,
                                    children: [
                                      // * Attribute
                                      textContainer(
                                          group.node.conditionAttributeName,
                                          500,
                                          context),
                                      const SizedBox(
                                        width: 20,
                                      ),
                                      // * Operator
                                      textContainer(
                                          operatorRepresents[group.node
                                                  .conditionOperatorNotation]
                                              .toString(),
                                          300,
                                          context),
                                      const SizedBox(
                                        width: 20,
                                      ),
                                      // * Value
                                      textContainer(group.node.condition.value,
                                          300, context)
                                    ],
                                  );
                                }
                              },
                            ),
                          ],
                        ),
                      ),
                    );
                  },
                ),
              );
            }
          }
        }));
  }
}
