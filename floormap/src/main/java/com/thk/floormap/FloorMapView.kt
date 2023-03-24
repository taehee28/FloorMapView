package com.thk.floormap

import android.content.Context
import android.widget.FrameLayout
import android.widget.ImageView
import com.thk.floormap.databinding.LayoutFloorMapViewBinding

class FloorMapView(context: Context) : FrameLayout(context, null, 0) {
    private val binding: LayoutFloorMapViewBinding

    val mapImage: ImageView
        get() = binding.mapImageView

    init {
        binding = LayoutFloorMapViewBinding.bind(inflate(context, R.layout.layout_floor_map_view, this))
    }

    fun setAdapter(adapter: MapIconAdapter<*, *>) {
        adapter.onAttachToFloorMapView(floorMapView = this)
    }

}