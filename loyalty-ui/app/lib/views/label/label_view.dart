import 'package:app/widgets/dialog.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import "package:app/controller.dart";
import 'package:tiengviet/tiengviet.dart';
import 'package:toggle_switch/toggle_switch.dart';

class LabelView extends StatelessWidget {
  LabelView({super.key});
  final DataController dataController = Get.find<DataController>();
  @override
  Widget build(BuildContext context) {
    dataController.refetch();
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 80,
        title: TextField(
          autofocus: true,
          decoration: const InputDecoration(
              labelText: "Tìm kiếm", border: OutlineInputBorder()),
          onChanged: (value) {
            var temp = TiengViet.parse(value.toLowerCase());
            if (value.isNotEmpty) {
              dataController.labels.value = dataController.originalLabels
                  .where((label) =>
                      TiengViet.parse(label.labelName.toLowerCase())
                          .contains(temp))
                  .toList();
              // Use the filteredLabels as needed
            } else {
              dataController.labels.value = dataController.originalLabels;
            }
          },
        ),
        actions: [
          SizedBox(
            width: 100,
            child: Obx(() {
              if (dataController.ruleStatus.value == "Khong") {
                return const Text(
                  "Rule không hoạt động",
                  maxLines: 2,
                  textAlign: TextAlign.center,
                  style: TextStyle(color: Colors.red),
                );
              } else if (dataController.ruleStatus.value == "Dang") {
                return Text(
                  "Rule đang hoạt động",
                  maxLines: 2,
                  textAlign: TextAlign.center,
                  style: TextStyle(color: Colors.yellow[900]),
                );
              } else {
                return const Text(
                  "Đã gán thành công",
                  maxLines: 2,
                  textAlign: TextAlign.center,
                  style: TextStyle(color: Colors.green),
                );
              }
            }),
          )
        ],
        automaticallyImplyLeading: false,
      ),
      body: Padding(
        padding: const EdgeInsets.only(left: 20),
        child: Obx(() {
          if (!dataController.isLoaded()) {
            return Center(
                child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                const CircularProgressIndicator(),
                const SizedBox(
                  height: 20,
                ),
                ...dataController.loadStatus.entries
                    .map((e) => e.key != "groups"
                        ? Text(
                            e.value == false
                                ? "Đang tải dữ liệu ${e.key}"
                                : "Đã tải xong dữ liệu ${e.key}",
                            style: TextStyle(
                                fontSize: 20,
                                color: e.value == false
                                    ? Colors.red
                                    : Colors.green),
                          )
                        : const SizedBox())
                    .toList()
              ],
            ));
          } else if (dataController.labels.isEmpty) {
            return const Center(
                child: Text(
              "Không có kết quả phù hợp",
              style: TextStyle(fontSize: 20),
            ));
          } else {
            return ListView.builder(
                itemCount: dataController.numberOfLabels,
                itemBuilder: ((context, index) => ListTile(
                      onTap: () {
                        dataController.forcusedLabelIndex = index;
                        Get.toNamed('/detail', id: 1);
                      },
                      title: Text(dataController.labelAt(index).labelName),
                      subtitle:
                          Text("Id ${dataController.labelAt(index).labelId}"),
                      trailing: ToggleSwitch(
                        customWidths: const [120, 40.0],
                        cornerRadius: 8,
                        fontSize: 16,
                        activeBgColors: [
                          [Get.theme.colorScheme.primary],
                          const [Colors.redAccent]
                        ],
                        activeFgColor: Colors.white,
                        inactiveBgColor: Colors.grey,
                        inactiveFgColor: Colors.white,
                        totalSwitches: 2,
                        initialLabelIndex:
                            dataController.labelAt(index).status ==
                                    "Đã kích hoạt"
                                ? 0
                                : 1,
                        labels: const ['Kích hoạt', ''],
                        icons: const [Icons.done, Icons.cancel],
                        onToggle: (value) {
                          if (value == 0) {
                            Get.dialog(createDialog(
                                context,
                                "Xác nhận kích hoạt",
                                "Bạn có muốn kích hoạt", () {
                              dataController.activateLabel(
                                dataController.labelAt(index).labelId,
                              );
                              Get.back();
                            }, () => Get.back(), "Kích hoạt", "Hủy"));
                          }
                        },
                      ),
                    )));
          }
        }),
      ),
      floatingActionButton: Row(
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          FloatingActionButton.extended(
            onPressed: () {
              dataController.newLabelObject.value =
                  dataController.createNewLabel();
              Get.toNamed('/add', id: 1);
            },
            label: const Text("Thêm Label"),
            icon: const Icon(Icons.add),
          ),
          const SizedBox(
            width: 10,
          ),
          FloatingActionButton.extended(
            onPressed: () {
              Get.toNamed('/tree', id: 1);
            },
            label: const Text("Xem cây"),
            icon: const Icon(Icons.forest),
          ),
          const SizedBox(
            width: 10,
          ),
          FloatingActionButton(
              tooltip: "Tải lại dữ liệu",
              onPressed: () {
                dataController.refetch();
              },
              child: const Icon(Icons.refresh)),
        ],
      ),
    );
  }
}
