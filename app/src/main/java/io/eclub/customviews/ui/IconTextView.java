package io.eclub.customviews.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import io.eclub.customviews.R;


//import com.lazada.core.view.FontTextView;
//// TODO: 2017/12/14
public class IconTextView extends AppCompatTextView {
    private boolean adjusted;

    public IconTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (!adjusted) {
            adjustStartCompoundDrawable();
            adjusted = true;
        }
    }

    private void adjustStartCompoundDrawable() {
        Drawable innerDrawable = getResources().getDrawable(R.drawable.black_icon);
        GravityCompoundDrawable gravityDrawable = new GravityCompoundDrawable(innerDrawable, (int) (getTextSize() / 2), getPaddingTop());
        innerDrawable.setBounds(0, 0, innerDrawable.getIntrinsicWidth(), innerDrawable.getIntrinsicHeight());
        gravityDrawable.setBounds(0, 0, innerDrawable.getIntrinsicWidth(), innerDrawable.getIntrinsicHeight());
        setCompoundDrawables(gravityDrawable, null, null, null);
    }

    private class GravityCompoundDrawable extends Drawable {
        private final Drawable drawable;
        private final int halfTextSize;
        private final int paddingTop;

        GravityCompoundDrawable(Drawable drawable, int halfTextSize, int paddingTop) {
            this.drawable = drawable;
            this.halfTextSize = halfTextSize;
            this.paddingTop = paddingTop;
        }

        @Override
        public int getIntrinsicWidth() {
            return drawable.getIntrinsicWidth();
        }

        @Override
        public int getIntrinsicHeight() {
            return drawable.getIntrinsicHeight();
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            int halfCanvas = canvas.getHeight() / 2;
            int halfDrawable = drawable.getIntrinsicHeight() / 2;

            // align to top
            canvas.save();
            int dy = -halfCanvas + halfTextSize + paddingTop + halfDrawable;
            canvas.translate(0, dy);
            drawable.draw(canvas);
            canvas.restore();
        }

        @Override
        public void setAlpha(int i) {

        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }
    }
}
