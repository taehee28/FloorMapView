package com.thk.sampleapp.views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.thk.floormap.MapIcon;
import com.thk.sampleapp.R;
import com.thk.sampleapp.databinding.ItemIconBaseBinding;
import com.thk.sampleapp.models.DeviceInfo;
import com.thk.sampleapp.models.DeviceStatus;

public abstract class BaseMapIcon extends MapIcon<DeviceStatus, DeviceInfo> {

    protected ItemIconBaseBinding binding;

    public BaseMapIcon(@NonNull Context context, @DrawableRes int deviceIconRes) {
        super(context);
        binding = ItemIconBaseBinding.bind(inflate(context, R.layout.item_icon_base, this));
        setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        binding.ivDeviceIcon.setImageResource(deviceIconRes);
    }

    @Override
    public void setupIcon(DeviceInfo deviceInfo) {
        setTag(deviceInfo.getId());

        setX(Float.parseFloat(deviceInfo.getX()));
        setY(Float.parseFloat(deviceInfo.getY()));

        binding.tvName.setText(deviceInfo.getName());

        floor = deviceInfo.getFloor();
    }

    @Override
    public void setSelected(boolean selected) {
        binding.tvName.setSelected(selected);
        super.setSelected(selected);
    }

    @Override
    public void setActivated(boolean activated) {
        // on/off 상태를 activated로 설정
        binding.ivDeviceIcon.setActivated(activated);
        super.setActivated(activated);
    }

    @Override
    public void setEnabled(boolean enabled) {
        binding.ivDeviceIcon.setEnabled(enabled);

        // disconnected 표시를 enable로 설정
        // enable 되어도 click은 가능해야 하기 때문에 super 호출하지 않음
        /*super.setEnabled(enabled);*/
    }
}
