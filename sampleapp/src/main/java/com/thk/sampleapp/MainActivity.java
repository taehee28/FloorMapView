package com.thk.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.thk.sampleapp.adapters.LightIconAdapter;
import com.thk.sampleapp.databinding.ActivityMainBinding;
import com.thk.sampleapp.models.TestData;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LightIconAdapter adapter = new LightIconAdapter();
        binding.floorMapView.setAdapter(adapter);
        adapter.setIconClickListener(v -> {

        });
        adapter.updateIconStatus();
    }
}