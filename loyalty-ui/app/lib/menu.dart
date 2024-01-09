import 'package:app/routes/result_route.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:app/controller.dart';
import 'package:app/routes/label_route.dart';

class Menu extends StatelessWidget {
  Menu({super.key, required this.hostName, required this.ruleHostName});
  final String hostName;
  final String ruleHostName;

  final ViewController viewController = Get.put(ViewController());
  final List<Widget> listView = <Widget>[
    // LabelView, LabelDetail
    Navigator(
      key: Get.nestedKey(1),
      initialRoute: LabelNestedRoute.view.route,
      onGenerateRoute: (settings) {
        return LabelNestedRoute.getPage(settings);
      },
    ),
    Navigator(
      key: Get.nestedKey(2),
      initialRoute: ResultNestedRoute.view.route,
      onGenerateRoute: (settings) {
        return ResultNestedRoute.getPage(settings);
      },
    ),
    const Center(child: Text("3")),
    const Center(child: Text("4")),
    const Center(child: Text("5")),
  ];

  final destinationMenuList = [
    const SizedBox(
      height: 20,
    ),
    const NavigationDrawerDestination(
      icon: Icon(Icons.account_tree_outlined),
      selectedIcon: Icon(Icons.account_tree),
      label: Text("Duyệt Label"),
    ),
    const NavigationDrawerDestination(
      icon: Icon(Icons.rule_folder_outlined),
      selectedIcon: Icon(Icons.rule_folder),
      label: Text("Duyệt Kết quả"),
    ),
    const NavigationDrawerDestination(
      selectedIcon: Icon(Icons.folder_shared),
      icon: Icon(Icons.folder_shared_outlined),
      label: Text("Duyệt KH"),
    ),
    const NavigationDrawerDestination(
      selectedIcon: Icon(Icons.mail),
      icon: Icon(Icons.mail_outline),
      label: Text("Duyệt yêu cầu"),
    ),
    const SizedBox(
      height: 20,
    ),
    Padding(
      padding: const EdgeInsets.all(20),
      child: FloatingActionButton.extended(
        heroTag: 2,
        label: const Text("Thoát"),
        onPressed: () {
          Get.back();
        },
        icon: const Icon(Icons.logout),
      ),
    )
  ];

  @override
  Widget build(BuildContext context) {
    // ignore: unused_local_variable
    final DataController dataController =
        Get.put(DataController(hostName: hostName, ruleHostName: ruleHostName));
    return Scaffold(
      body: Obx(
        () => Row(
          children: [
            SizedBox(
              width: 250,
              child: NavigationDrawer(
                  selectedIndex: viewController.currentPage,
                  onDestinationSelected: (pageId) {
                    viewController.moveToPage(pageId);
                  },
                  children: destinationMenuList),
            ),
            Expanded(child: listView[viewController.currentPage])
          ],
        ),
      ),
    );
  }
}
