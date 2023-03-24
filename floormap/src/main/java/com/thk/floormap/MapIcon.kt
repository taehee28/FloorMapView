package com.thk.floormap

import android.content.Context
import android.widget.FrameLayout

abstract class MapIcon<T>(context: Context) : FrameLayout(context, null, 0) {

    abstract fun update(data: T)

}