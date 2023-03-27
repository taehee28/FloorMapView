package com.thk.floormap

import android.content.Context
import android.view.View
import android.widget.FrameLayout

abstract class MapIcon<ST, INFO>(context: Context) : FrameLayout(context, null, 0) {
    lateinit var floor: String
        protected set

    abstract fun setupIcon(info: INFO)
    abstract fun updateIcon(data: ST)

}
