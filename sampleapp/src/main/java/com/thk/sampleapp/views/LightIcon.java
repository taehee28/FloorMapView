package com.thk.sampleapp.views;

import android.content.Context;

import androidx.annotation.NonNull;

import com.thk.floormap.MapIcon;
import com.thk.sampleapp.R;
import com.thk.sampleapp.databinding.ItemIconLightBinding;
import com.thk.sampleapp.models.DeviceInfo;
import com.thk.sampleapp.models.DeviceStatus;
import com.thk.sampleapp.models.TestData;

public class LightIcon extends MapIcon<DeviceStatus, DeviceInfo> {

    /*
    note: MapIcon을 감싸는 BaseIcon 클래스를 하나 만들고, 해당 클래스를 상속받아서
          각 디바이스 별 아이콘을 만들도록하면 중복 코드를 추출할 수 있을 듯
     */

    private ItemIconLightBinding binding;

    public LightIcon(@NonNull Context context) {
        super(context);

        binding = ItemIconLightBinding.bind(inflate(context, R.layout.item_icon_light, this));
    }

    @Override
    public void setupIcon(DeviceInfo deviceInfo) {
        setTag(deviceInfo.getId());

        setX(Float.parseFloat(deviceInfo.getX()));
        setY(Float.parseFloat(deviceInfo.getY()));

        // todo 이름 설정
        binding.btnIcon.setText(deviceInfo.getName());

        floor = deviceInfo.getFloor();
    }

    @Override
    public void updateIcon(DeviceStatus data) {
        setEnabled(data.getStatus().equals(TestData.CONNECTED));
    }

    @Override
    public void setEnabled(boolean enabled) {
        binding.btnIcon.setEnabled(enabled);
        super.setEnabled(enabled);
    }
}
