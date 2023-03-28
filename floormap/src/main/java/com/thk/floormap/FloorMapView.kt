package com.thk.floormap

import android.content.Context
import android.util.AttributeSet
import android.view.View
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

    private val binding: LayoutFloorMapViewBinding =
        LayoutFloorMapViewBinding.bind(inflate(context, R.layout.layout_floor_map_view, this))

    val mapImage: ImageView
        get() = binding.mapImageView

    /**
     * 어떤 층을 표시하고 있는지에 대한 변수
     */
    private var floor = "1"

    /**
     * 층 변경하는 람다를 저장하는 변수
     */
    private var changeFloor: (() -> Unit)? = null

    fun setAdapter(adapter: MapIconAdapter<*,*>) {
        adapter.onAttachToFloorMapView(floorMapView = this)

        changeFloor = {
            floor = if (floor == "1") "2" else "1"
            adapter.changeFloor(floor)
        }

        binding.btnChangeFloor.setOnClickListener { changeFloor?.invoke() }
    }

    /**
     * 층 변경 버튼의 visibility 설정
     */
    fun setFloorButtonVisibility(show: Boolean) {
        binding.btnChangeFloor.visibility = if (show) VISIBLE else GONE
    }

    /**
     * 층 변경 버튼의 OnClickListener 설정
     */
    fun setFloorButtonClickListener(listener: View.OnClickListener) {
        binding.btnChangeFloor.setOnClickListener {
            listener.onClick(it)
            changeFloor?.invoke()
        }
    }

}