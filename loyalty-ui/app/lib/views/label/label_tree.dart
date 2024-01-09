import 'package:app/controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:graphview/GraphView.dart' as graph;

class LabelTree extends StatelessWidget {
  LabelTree({super.key});
  final graph.BuchheimWalkerConfiguration builder =
      graph.BuchheimWalkerConfiguration();
  final DataController dataController = Get.find<DataController>();
  @override
  Widget build(BuildContext context) {
    builder
      ..siblingSeparation = (30)
      ..levelSeparation = (30)
      ..subtreeSeparation = (30)
      ..orientation =
          (graph.BuchheimWalkerConfiguration.ORIENTATION_LEFT_RIGHT);
    return Scaffold(
        appBar: AppBar(
          title: const Text("Cây Label"),
          automaticallyImplyLeading: false,
          leading: IconButton(
              onPressed: () {
                Get.back(id: 1);
              },
              icon: const Icon(Icons.arrow_back)),
        ),
        body: Obx(() {
          if (dataController.originalLabels.isNotEmpty) {
            return InteractiveViewer(
                constrained: false,
                trackpadScrollCausesScale: false,
                boundaryMargin: const EdgeInsets.all(50),
                maxScale: 5,
                minScale: 1,
                child: graph.GraphView(
                  graph: dataController.graph.value,
                  algorithm: graph.BuchheimWalkerAlgorithm(
                      builder, graph.TreeEdgeRenderer(builder)),
                  paint: Paint()
                    ..color = Theme.of(context).primaryColor
                    ..strokeWidth = 1
                    ..style = PaintingStyle.stroke,
                  builder: (graph.Node node) {
                    // I can decide what widget should be shown here based on the id
                    var a = node.key!.value as int?;
                    return InkWell(
                        onTap: () {
                          dataController.forcusedLabelIndex =
                              dataController.labelIndexFromId(a);
                          Get.toNamed('/detail', id: 1);
                        },
                        child: Row(children: [
                          Container(
                            width: 150,
                            height: 100,
                            padding: const EdgeInsets.all(8),
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(15),
                              color: Theme.of(context).primaryColor,
                            ),
                            child: Center(
                              child: Text(
                                dataController.labelNameById(a!),
                                style: const TextStyle(fontSize: 20),
                                overflow: TextOverflow.ellipsis,
                                maxLines: 3,
                              ),
                            ),
                          ),
                          IconButton(
                            icon: const Icon(Icons.add),
                            onPressed: () {
                              dataController.newLabelObject.value =
                                  dataController.createNewLabel();
                              dataController.newLabelObject.value.headId = a;
                              Get.toNamed('/add', id: 1);
                            },
                          ),
                        ]));
                  },
                ));
          } else {
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
                      "Đang tải dữ liệu Label",
                      style: TextStyle(fontSize: 20, color: Colors.red),
                    )
                  ]),
            );
          }
        }));
  }
}
