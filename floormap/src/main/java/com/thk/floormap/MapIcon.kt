package com.thk.floormap

import android.view.View
import android.widget.FrameLayout

abstract class MapIcon<ST, INFO>(iconView: View) : FrameLayout(iconView.context, null, 0) {

    abstract fun setupIcon(info: INFO)
    abstract fun updateIcon(data: ST)

}
