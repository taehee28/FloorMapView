package com.thk.floormap

import android.content.Context
import android.view.View

abstract class MapIconAdapter<INFO, IC: MapIcon<*, *>> {

    protected var floorMapView: FloorMapView? = null

    private var selectedIconTag: String = "";
    var iconClickListener: View.OnClickListener? = null

    abstract fun onCreateIcon(context: Context, info: INFO): IC

    abstract fun onBindIcon(icon: IC, deviceNumber: String)

    abstract fun getInfoList(): List<INFO>

    abstract fun getTagId(info: INFO): String

    /**
     * 평면도에 추가한 아이콘 전체의 상태를 업데이트 합니다.
     */
    fun updateIconStatus() {
        getInfoList().forEach { info ->
            val id = getTagId(info)
            updateIconStatus(deviceNumber = id)
        }
    }

    /**
     * 평면도에 추가한 아이콘 중 특정 디바이스의 아이콘을 찾아 상태를 업데이트 합니다.
     */
    fun updateIconStatus(deviceNumber: String) = mapViewNotNull { mapView ->
        mapView.findViewWithTag<IC>(deviceNumber)?.also { icon ->
            onBindIcon(icon = icon, deviceNumber = deviceNumber)
        }
    }

    /**
     * 평면도의 층을 변경합니다. 해당 층에 속하는 디바이스 아이콘만 표시합니다.
     */
    fun changeFloor(floor: String) = mapViewNotNull { mapView ->
        selectedIconTag = ""

        getInfoList().forEach { info ->
            val id = getTagId(info)
            val icon = mapView.findViewWithTag<IC>(id)
            if (icon != null) {
                icon.visibility = if (icon.floor == floor) View.VISIBLE else View.GONE
            }

            if (selectedIconTag.isBlank()) {
                icon.callOnClick()
            }
        }
    }

    fun onAttachToFloorMapView(floorMapView: FloorMapView) {
        this.floorMapView = floorMapView

        initIcons()
    }

    private fun initIcons() = mapViewNotNull { mapView ->
        getInfoList().forEach { info ->
            val icon = onCreateIcon(mapView.context, info).apply {
                visibility = if (floor == "1") View.VISIBLE else View.GONE

                setOnClickListener { view ->
                    mapView.findViewWithTag<View>(selectedIconTag)?.also { selectedView ->
                        selectedView.isSelected = false
                    }

                    view.isSelected = true
                    selectedIconTag = view.tag as String

                    iconClickListener?.onClick(view)
                }
            }

            if (selectedIconTag.isBlank()) {
                icon.isSelected = true
                selectedIconTag = icon.tag as String
            }

            mapView.addView(icon)
        }
    }

    private inline fun mapViewNotNull(block: (FloorMapView) -> Unit) {
        floorMapView?.also { block(it) }
    }
}