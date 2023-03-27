package com.thk.floormap

import android.content.Context
import android.view.View
import android.widget.FrameLayout

abstract class MapIcon<ST, INFO>(context: Context) : FrameLayout(context, null, 0) {
    protected lateinit var floor: String

    abstract fun setupIcon(info: INFO)
    abstract fun updateIcon(data: ST)

}
