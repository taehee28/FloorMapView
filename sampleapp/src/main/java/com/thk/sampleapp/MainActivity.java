package com.thk.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.thk.sampleapp.adapters.LightIconAdapter;
import com.thk.sampleapp.databinding.ActivityMainBinding;
import com.thk.sampleapp.models.DeviceStatus;
import com.thk.sampleapp.models.TestData;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private String selectedDeviceNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LightIconAdapter adapter = new LightIconAdapter();
        binding.floorMapView.setAdapter(adapter);
        adapter.setIconClickListener(v -> {
            selectedDeviceNumber = (String) v.getTag();
            DeviceStatus status = TestData.getDevice(selectedDeviceNumber);

            if (status != null) {
                if (status.getStatus().equals(TestData.DISCONNECTED)) {
                    binding.switchPower.setChecked(false);
                    binding.switchPower.setEnabled(false);
                } else {
                    binding.switchPower.setEnabled(true);
                    binding.switchPower.setChecked(status.getSwitchBinary().equals(TestData.ON));
                }
            }
        });
        adapter.updateIconStatus();

        binding.switchPower.setOnClickListener(view -> {
            DeviceStatus status = TestData.getDevice(selectedDeviceNumber);
            if (status != null) {
                boolean isOn = status.getSwitchBinary().equals(TestData.ON);
                status.setSwitchBinary(isOn ? TestData.OFF : TestData.ON);

                adapter.updateIconStatus(selectedDeviceNumber);
            }
        });
    }
}