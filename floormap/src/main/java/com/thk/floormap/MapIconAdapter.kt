package com.thk.floormap

import android.content.Context
import android.view.View

abstract class MapIconAdapter<INFO, IC: MapIcon<*, *>> {

    private var floorMapView: FloorMapView? = null

    private var selectedIconTag: String = "";
    var iconClickListener: View.OnClickListener? = null

    abstract fun onCreateIcon(context: Context, info: INFO): IC

    abstract fun onBindIcon(icon: IC, deviceNumber: String)

    abstract fun getInfoList(): List<INFO>

    abstract fun getDeviceNumber(info: INFO): String

    /**
     * 평면도에 추가한 아이콘 전체의 상태를 업데이트 합니다.
     */
    fun updateIconStatus() {
        getInfoList().forEach { info ->
            val number = getDeviceNumber(info)
            updateIconStatus(deviceNumber = number)
        }
    }

    /**
     * 평면도에 추가한 아이콘 중 특정 디바이스의 아이콘을 찾아 상태를 업데이트 합니다.
     */
    fun updateIconStatus(deviceNumber: String) = mapViewNotNull { mapView ->
        mapView.findViewWithTag<IC>(deviceNumber)?.also { icon ->
            onBindIcon(icon = icon, deviceNumber = deviceNumber)

            // selected 상태면 onClick 호출함 -> 바깥 업데이트할 수 있도록 신호 
            if (icon.isSelected) icon.callOnClick()
        }
    }

    /**
     * 평면도의 층을 변경합니다. 해당 층에 속하는 디바이스 아이콘만 표시합니다.
     */
    fun changeFloor(floor: String) = mapViewNotNull { mapView ->
        // 바뀐 층의 가장 첫번째 아이콘을 selected 처리했는지 표시하는 flag  
        var setSelectedFlag = false

        getInfoList().forEach { info ->
            val number = getDeviceNumber(info)
            val icon = mapView.findViewWithTag<IC>(number)
            if (icon != null) {
                icon.visibility = if (icon.floor == floor) View.VISIBLE else View.GONE
                
                if (setSelectedFlag.not() && icon.floor == floor) {
                    icon.callOnClick()
                    setSelectedFlag = true
                }
            }

        }
    }

    internal fun onAttachToFloorMapView(floorMapView: FloorMapView) {
        this.floorMapView = floorMapView

        initIcons()
    }

    private fun initIcons() = mapViewNotNull { mapView ->
        getInfoList().forEach { info ->
            val icon = onCreateIcon(mapView.context, info).apply {

                // 처음은 1층에 해당하는 아이콘을 표시함
                visibility = if (floor == "1") View.VISIBLE else View.GONE

                setOnClickListener { view ->
                    // 이전에 selected한 아이콘의 selected 해제 처리
                    mapView.findViewWithTag<View>(selectedIconTag)?.also { selectedView ->
                        selectedView.isSelected = false
                    }

                    view.isSelected = true
                    selectedIconTag = view.tag as String

                    iconClickListener?.onClick(view)
                }
            }

            // 첫번째로 그리는 아이콘을 selected 처리
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