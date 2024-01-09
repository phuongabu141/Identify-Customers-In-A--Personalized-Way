import 'package:flutter/material.dart';
import 'package:get/get.dart';
import "menu.dart";

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        primaryColor: Colors.purple[100],
        colorScheme: ColorScheme.fromSeed(
          seedColor: Colors.purple[100]!,
          brightness: Brightness.light,
        ),
      ),
      home: const HostPage(),
    );
  }
}

class HostPage extends StatelessWidget {
  const HostPage({super.key});

  @override
  Widget build(BuildContext context) {
    String hostName = "a11e-115-79-28-78.ngrok-free.app";
    String ruleHostName = "127.0.0.1:8000";
    return Scaffold(
        body: Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          SizedBox(
              width: 500,
              child: TextField(
                controller: TextEditingController(text: hostName),
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Nhập Database API hostname',
                ),
                onChanged: (value) {
                  hostName = value;
                },
              )),
          const SizedBox(
            height: 20,
          ),
          SizedBox(
              width: 500,
              child: TextField(
                controller: TextEditingController(text: ruleHostName),
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Nhập Rule API hostname',
                ),
                onChanged: (value) {
                  hostName = value;
                },
              )),
          const SizedBox(
            height: 20,
          ),
          FloatingActionButton.extended(
              heroTag: 1,
              onPressed: () {
                Get.to(() => Menu(
                      hostName: hostName,
                      ruleHostName: ruleHostName,
                    ));
              },
              label: const Text("Chuyển đến Menu"))
        ],
      ),
    ));
  }
}
