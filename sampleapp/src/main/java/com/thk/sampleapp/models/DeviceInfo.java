package com.thk.sampleapp.models;

public class DeviceInfo {
    private String id;
    private String device;
    private String name;
    private String x;
    private String y;
    private String floor;

    public DeviceInfo(String id, String device, String name, String x, String y, String floor) {
        this.id = id;
        this.device = device;
        this.name = name;
        this.x = x;
        this.y = y;
        this.floor = floor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
