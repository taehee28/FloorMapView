package com.thk.floormap

abstract class MapIconAdapter<ST, INFO, IC: MapIcon<ST, INFO>> {

    protected var floorMapView: FloorMapView? = null

    abstract fun drawIcons()
    abstract fun updateIcon(deviceNumber: String)
    abstract fun changeFloor(floor: String)

    fun onAttachToFloorMapView(floorMapView: FloorMapView) {
        this.floorMapView = floorMapView
    }
}