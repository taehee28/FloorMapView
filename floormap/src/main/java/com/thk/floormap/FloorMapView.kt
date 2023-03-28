package com.thk.floormap

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.thk.floormap.databinding.LayoutFloorMapViewBinding

class FloorMapView : FrameLayout {
    constructor(context: Context) : this(context, null)
    constructor(
        context: Context,
        attrs: AttributeSet?,
    ) : this(context, attrs, 0)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
    ) : super(context, attrs, defStyleAttr)

    private val binding: LayoutFloorMapViewBinding

    val mapImage: ImageView
        get() = binding.mapImageView

    private var floor = "1"

    init {
        binding = LayoutFloorMapViewBinding.bind(inflate(context, R.layout.layout_floor_map_view, this))
    }

    fun setAdapter(adapter: MapIconAdapter<*,*>) {
        adapter.onAttachToFloorMapView(floorMapView = this)

        binding.btnChangeFloor.setOnClickListener {
            floor = if (floor == "1") "2" else "1"
            adapter.changeFloor(floor)
        }
    }

    fun setFloorButtonVisibility(show: Boolean) {
        binding.btnChangeFloor.visibility = if (show) VISIBLE else GONE
    }

}