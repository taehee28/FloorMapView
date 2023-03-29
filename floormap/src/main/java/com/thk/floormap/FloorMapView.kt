package com.thk.floormap

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.thk.floormap.databinding.LayoutFloorMapViewBinding

class FloorMapView : FrameLayout {

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

    constructor(context: Context) : super(context)
    constructor(
        context: Context,
        attrs: AttributeSet?,
    ) : super(context, attrs) {
        init(attrs = attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
    ) : super(context, attrs, defStyleAttr) {
        init(attrs = attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FloorMapView, 0, 0)

        binding.btnChangeFloor.apply {
            // match_parent와 wrap_content를 처리할 수 있는 getLayoutDimension 메서드 사용
            val btnWidth = typedArray.getLayoutDimension(R.styleable.FloorMapView_floorButton_width, LayoutParams.WRAP_CONTENT)
            val btnHeight = typedArray.getLayoutDimension(R.styleable.FloorMapView_floorButton_height, LayoutParams.WRAP_CONTENT)

            layoutParams = LayoutParams(btnWidth, btnHeight).apply {
                val layoutGravity = typedArray.getInteger(R.styleable.FloorMapView_floorButton_layout_gravity, Gravity.START or Gravity.TOP)
                val marginTop = typedArray.getDimensionPixelSize(R.styleable.FloorMapView_floorButton_marginTop, 0)
                val marginBottom = typedArray.getDimensionPixelSize(R.styleable.FloorMapView_floorButton_marginBottom, 0)
                val marginStart = typedArray.getDimensionPixelSize(R.styleable.FloorMapView_floorButton_marginStart, 0)
                val marginEnd = typedArray.getDimensionPixelSize(R.styleable.FloorMapView_floorButton_marginEnd, 0)

                this.gravity = layoutGravity

                this.topMargin = marginTop
                this.bottomMargin = marginBottom
                setMarginStart(marginStart)
                setMarginEnd(marginEnd)
            }


            val visibility = typedArray.getInteger(R.styleable.FloorMapView_floorButton_visibility, View.VISIBLE)
            val text = typedArray.getString(R.styleable.FloorMapView_floorButton_text) ?: ""
            val textSize = typedArray.getDimensionPixelSize(R.styleable.FloorMapView_floorButton_textSize, resources.getDimensionPixelSize(R.dimen.floorButton_textSize_default))
            val textColor = typedArray.getColor(R.styleable.FloorMapView_floorButton_textColor, ContextCompat.getColor(context, R.color.floorButton_textColor_default))
            val gravity = typedArray.getInteger(R.styleable.FloorMapView_floorButton_gravity, Gravity.CENTER)

            this.visibility = visibility
            this.text = text
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
            setTextColor(textColor)
            this.gravity = gravity

            // 색상 값인지 drawable 파일 구분할 필요 없이 전부 Drawable로 가져올 수 있음
            val backgroundDrawable = typedArray.getDrawable(R.styleable.FloorMapView_floorButton_background)
            if (typedArray.hasValueOrEmpty(R.styleable.FloorMapView_floorButton_background)) {
                this.background = backgroundDrawable
            }

            // res/font 폴더에 있는 폰트는 리소스 아이디로 가져와서 적용
            val fontResId = typedArray.getResourceId(R.styleable.FloorMapView_floorButton_textFont, 0);
            if (fontResId != 0) typeface = ResourcesCompat.getFont(context, fontResId)

        }

        typedArray.recycle()
    }

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