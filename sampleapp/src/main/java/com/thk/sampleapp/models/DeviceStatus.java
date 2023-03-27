package com.thk.sampleapp.models;

public class DeviceStatus {
    private String id;
    private String device;
    private String status;
    private String switchBinary;

    public DeviceStatus(String id, String device, String status, String switchBinary) {
        this.id = id;
        this.device = device;
        this.status = status;
        this.switchBinary = switchBinary;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSwitchBinary() {
        return switchBinary;
    }

    public void setSwitchBinary(String switchBinary) {
        this.switchBinary = switchBinary;
    }
}
