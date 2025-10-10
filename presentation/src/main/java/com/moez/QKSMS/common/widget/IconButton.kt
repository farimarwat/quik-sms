package com.moez.QKSMS.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import dev.octoshrimpy.quik.R


class IconButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val iconView: ImageView
    private val textView: TextView
    private val rootLayout: LinearLayout

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_iconbutton, this, true)
        rootLayout = findViewById(R.id.rootLayout)
        iconView = findViewById(R.id.iconView)
        textView = findViewById(R.id.textView)

        context.theme.obtainStyledAttributes(attrs, R.styleable.IconButton, 0, 0).apply {
            try {
                val iconRes = getResourceId(R.styleable.IconButton_buttonIcon, -1)
                val iconTint = getColor(R.styleable.IconButton_buttonIconTint,0)
                val iconSize = getDimension(R.styleable.IconButton_buttonIconSize, 48f)
                iconView.layoutParams.width = iconSize.toInt()
                iconView.layoutParams.height = iconSize.toInt()

                val backgroundColor = getColor(R.styleable.IconButton_buttonBackgroundColor, 0)
                val backgroundRes = getResourceId(R.styleable.IconButton_buttonBackground, -1)
                val text = getString(R.styleable.IconButton_buttonText)
                val textSize = getDimension(R.styleable.IconButton_buttonTextSize, 24f)

                if (iconRes != -1) iconView.setImageResource(iconRes)
                if (iconTint != 0) iconView.setColorFilter(iconTint)
                if (backgroundColor != 0) rootLayout.setBackgroundColor(backgroundColor)
                if (backgroundRes != -1) rootLayout.setBackgroundResource(backgroundRes)
                textView.text = text ?: ""
                textView.textSize = textSize / resources.displayMetrics.scaledDensity
            } finally {
                recycle()
            }
        }
    }

    // Optional setters for programmatic usage
    fun setIcon(resId: Int) = iconView.setImageResource(resId)
    fun setText(text: String) { textView.text = text }
    fun setBackgroundColorRes(colorRes: Int) {
        rootLayout.setBackgroundColor(ContextCompat.getColor(context, colorRes))
    }
}
