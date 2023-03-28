package com.thk.sampleapp.views;

import android.content.Context;

import androidx.annotation.NonNull;

import com.thk.floormap.MapIcon;
import com.thk.sampleapp.R;
import com.thk.sampleapp.models.DeviceInfo;
import com.thk.sampleapp.models.DeviceStatus;
import com.thk.sampleapp.models.TestData;

public class LightIcon extends BaseMapIcon {

    public LightIcon(@NonNull Context context) {
        super(context, R.drawable.selector_ic_mapicon_device_light);
    }

    @Override
    public void updateIcon(DeviceStatus data) {
        setActivated(data.getSwitchBinary().equals(TestData.ON));
        setEnabled(data.getStatus().equals(TestData.CONNECTED));
    }
}
