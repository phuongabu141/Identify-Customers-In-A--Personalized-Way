import 'package:app/controller.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class ResultView extends StatelessWidget {
  ResultView({super.key});

  final DataController dataController = Get.find<DataController>();

  @override
  Widget build(BuildContext context) {
    dataController.fetchALlResult();
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        title: const Text("Duyệt Kết quả"),
      ),
      body: Obx(() {
        if (dataController.results.isEmpty) {
          return const Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                CircularProgressIndicator(),
                SizedBox(
                  height: 20,
                ),
                Text("Đang tải dữ liệu kết quả...",
                    style: TextStyle(fontSize: 20))
              ],
            ),
          );
        } else {
          return ListView.builder(
            itemCount: dataController.results.length,
            itemBuilder: (context, index) {
              return Card(
                child: ListTile(
                  onTap: () {
                    dataController.forcusedResultIndex = index;
                    Get.toNamed('/detail', id: 2);
                  },
                  tileColor: dataController.results[index].isTrue
                      ? Colors.green[200]
                      : Colors.red[100],
                  title: Text(
                      "[Mã KQ: ${dataController.results[index].resultId}][Mã Label ${dataController.results[index].labelId.toString()}] ${dataController.results[index].labelName}"),
                  subtitle: Text(
                      "Mã KH: ${dataController.results[index].customerId} (${dataController.results[index].datetime})"),
                  trailing: Text(
                    dataController.results[index].isTrue
                        ? "Thỏa"
                        : "Không thỏa",
                    style: const TextStyle(fontSize: 20),
                  ),
                ),
              );
            },
          );
        }
      }),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          dataController.fetchALlResult();
        },
        child: const Icon(Icons.refresh),
      ),
    );
  }
}
