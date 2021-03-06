package minhna.android.androidarchitecturecomponent.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by minhnguyen on 3/2/18.
 */

//This one is useful when use in StaggeredGridLayout. When remove the image in the item or on loading,
// that item with no picture still remain in that position due to the ratio.
class AspectRatioImageView(context: Context, attrs: AttributeSet? = null, aspectRatio: Float = 0.0f) : ImageView(context, attrs) {
    private var mAspectRatio: Float = 0.0f

    init {
        mAspectRatio = aspectRatio
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = Integer.valueOf (width * measuredHeight)
        setMeasuredDimension(width, height)
    }
}