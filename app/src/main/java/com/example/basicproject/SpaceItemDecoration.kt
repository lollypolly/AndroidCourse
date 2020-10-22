package com.example.basicproject

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    context: Context
) : RecyclerView.ItemDecoration() {

    private val spacingWidthPx: Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 16.toFloat(),
        context.resources.displayMetrics
    ).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val middleMargin = spacingWidthPx / 4
        when {
            position == 0 -> {
                outRect.top = spacingWidthPx
                outRect.bottom = middleMargin
            }
            position > 0 && position == state.itemCount - 1 -> {
                outRect.top = middleMargin
                outRect.bottom = spacingWidthPx
            }
            else -> {
                outRect.top = middleMargin
                outRect.bottom = middleMargin
            }
        }
    }
}