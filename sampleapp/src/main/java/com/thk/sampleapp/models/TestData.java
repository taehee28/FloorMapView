package com.thk.sampleapp.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {
    public static final String DEVICE_LIGHT = "light";
    public static final String DEVICE_HEAT = "heat";

    public static final String CONNECTED = "connected";
    public static final String DISCONNECTED = "disconnected";

    public static final String ON = "on";
    public static final String OFF = "off";

    public static List<DeviceStatus> deviceStatusList = Arrays.asList(
            new DeviceStatus("1", DEVICE_LIGHT, CONNECTED, ON),
            new DeviceStatus("2", DEVICE_LIGHT, DISCONNECTED, ON),
            new DeviceStatus("3", DEVICE_LIGHT, CONNECTED, ON),
            new DeviceStatus("4", DEVICE_LIGHT, CONNECTED, OFF),
            new DeviceStatus("21", DEVICE_HEAT, CONNECTED, ON),
            new DeviceStatus("22", DEVICE_HEAT, CONNECTED, ON),
            new DeviceStatus("23", DEVICE_HEAT, CONNECTED, ON),
            new DeviceStatus("24", DEVICE_HEAT, CONNECTED, ON)
    );

    public static List<DeviceInfo> lightDeviceInfoList = Arrays.asList(
            new DeviceInfo("1", DEVICE_LIGHT, "1번", "100", "100", "1"),
            new DeviceInfo("2", DEVICE_LIGHT, "2번", "300", "300", "1"),
            new DeviceInfo("3", DEVICE_LIGHT, "3번", "200", "300", "2"),
            new DeviceInfo("4", DEVICE_LIGHT, "4번", "300", "100", "2")
    );

    public static List<DeviceInfo> heatDeviceInfoList = Arrays.asList(
            new DeviceInfo("21", DEVICE_HEAT, "21번", "100", "100", "1"),
            new DeviceInfo("22", DEVICE_HEAT, "22번", "300", "300", "1"),
            new DeviceInfo("23", DEVICE_HEAT, "23번", "200", "300", "2"),
            new DeviceInfo("24", DEVICE_HEAT, "24번", "300", "100", "2")
    );

    public static List<DeviceStatus> getDeviceList(List<DeviceInfo> infoList) {
        List<DeviceStatus> list = new ArrayList<>();

        for (DeviceInfo info : infoList) {
            for (DeviceStatus device : deviceStatusList) {
                if (info.getId().equals(device.getId())) {
                    list.add(device);
                }
            }
        }

        return list;
    }

    public static DeviceStatus getDevice(String deviceNumber) {
        DeviceStatus result = null;

        for (DeviceStatus device : deviceStatusList) {
            if (device.getId().equals(deviceNumber)) {
                result = device;
                break;
            }
        }

        return result;
    }

}
