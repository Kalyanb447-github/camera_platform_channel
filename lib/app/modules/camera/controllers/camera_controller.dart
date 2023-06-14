import 'package:flutter/services.dart';
import 'package:get/get.dart';

class CameraController extends GetxController {
  RxString batteryLevel = "Tap button to fetch battery level".obs;
  static const batteryChannel = MethodChannel("platform_channel");

  @override
  void onInit() {
    super.onInit();
  }

  Future<void> getBatteryLevel() async {
    Map<String, String> arguments = {"name": "Android"};
    String newBatteryLevel =
        await batteryChannel.invokeMethod("getBatteryLevel", arguments);
    batteryLevel.value = newBatteryLevel;
  }

  @override
  void onReady() {
    super.onReady();
  }

  @override
  void onClose() {
    super.onClose();
  }
}
