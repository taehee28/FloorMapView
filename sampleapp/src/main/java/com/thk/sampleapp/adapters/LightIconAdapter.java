package com.thk.sampleapp.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.thk.floormap.MapIconAdapter;
import com.thk.sampleapp.models.DeviceInfo;
import com.thk.sampleapp.models.DeviceStatus;
import com.thk.sampleapp.models.TestData;
import com.thk.sampleapp.views.LightIcon;

import java.util.List;

public class LightIconAdapter extends MapIconAdapter<DeviceInfo, LightIcon> {

    @NonNull
    @Override
    public LightIcon onCreateIcon(@NonNull Context context, DeviceInfo deviceInfo) {
        LightIcon icon = new LightIcon(context);
        icon.setupIcon(deviceInfo);

        return icon;
    }

    @Override
    public void onBindIcon(@NonNull LightIcon icon, @NonNull String deviceNumber) {
        DeviceStatus status = TestData.getDevice(deviceNumber);
        icon.updateIcon(status);
    }

    @NonNull
    @Override
    public List<DeviceInfo> getInfoList() {
        return TestData.lightDeviceInfoList;
    }

    @NonNull
    @Override
    public String getTagId(DeviceInfo deviceInfo) {
        return deviceInfo.getId();
    }
}
