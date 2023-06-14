import 'package:flutter/material.dart';

import 'package:get/get.dart';

import '../controllers/camera_controller.dart';

/// GetBatteryLevel fetches the current battery level of the android device.
class CameraView extends GetView<CameraController> {
  const CameraView({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const SizedBox(height: 16),
          Obx(() {
            return Text(
              controller.batteryLevel.value,
              textAlign: TextAlign.center,
              style: const TextStyle(
                color: Colors.deepPurple,
                fontSize: 30,
              ),
            );
          }),
          const SizedBox(height: 16),
          ElevatedButton(
            onPressed: controller.getBatteryLevel,
            child: const Text("Get Battery Level"),
          ),
        ],
      ),
    );
  }
}
