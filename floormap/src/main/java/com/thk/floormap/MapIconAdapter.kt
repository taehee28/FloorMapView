package com.thk.floormap

abstract class MapIconAdapter<T, out IC: MapIcon<T>> {
    protected var floorMapView: FloorMapView? = null

    fun onAttachToFloorMapView(floorMapView: FloorMapView) {
        this.floorMapView = floorMapView
    }
}