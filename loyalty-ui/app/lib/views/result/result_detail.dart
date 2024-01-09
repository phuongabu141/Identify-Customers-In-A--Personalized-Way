import 'package:app/constants.dart';
import 'package:app/model.dart';
import 'package:flutter/material.dart';
import 'package:flutter_fancy_tree_view/flutter_fancy_tree_view.dart';
import 'package:get/get.dart';
import 'package:app/controller.dart';
import 'package:app/widgets/all.dart';

class ResultDetail extends StatelessWidget {
  ResultDetail({super.key});
  final DataController dataController = Get.find<DataController>();
  @override
  Widget build(BuildContext context) {
    dataController.fetchResultDetail(dataController.forcusedResult.resultId);
    final treeController = TreeController<Group>(
      roots: dataController.forcusedResult.groups,
      childrenProvider: (Group node) => node.children,
    );
    treeController.expandAll();
    return Scaffold(
      appBar: AppBar(
        title: Text(dataController.forcusedResult.labelName),
        leading: BackButton(
          onPressed: () {
            Get.back(id: 2);
          },
        ),
      ),
      body: Obx(() {
        if (!dataController.forcusedResult.isHaveGroup) {
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
          if (dataController.forcusedResult.groups.isEmpty) {
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
                                        style: const TextStyle(fontSize: 20)));
                              } else {
                                return Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceEvenly,
                                  children: [
                                    // * Attribute
                                    Column(
                                      mainAxisAlignment:
                                          MainAxisAlignment.center,
                                      crossAxisAlignment:
                                          CrossAxisAlignment.center,
                                      children: [
                                        textContainer(
                                            group.node.conditionAttributeName,
                                            500,
                                            context,
                                            color: group.node.isTrue
                                                ? null
                                                : Colors.red),
                                        Text(
                                            "Giá trị thuộc tính lúc xét: ${group.node.customerValue}",
                                            style:
                                                const TextStyle(fontSize: 20))
                                      ],
                                    ),
                                    const SizedBox(
                                      width: 20,
                                    ),
                                    // * Operator
                                    textContainer(
                                        operatorRepresents[group
                                                .node.conditionOperatorNotation]
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
      }),
    );
  }
}
