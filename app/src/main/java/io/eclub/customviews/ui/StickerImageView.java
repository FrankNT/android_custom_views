package io.eclub.customviews.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import io.eclub.customviews.R;
import io.eclub.customviews.util.UIUtils;


public class StickerImageView extends android.support.v7.widget.AppCompatImageView {

    public static final int DEFAULT_SIZE = 58;
    public static final int DEFAULT_PADDING = 2;
    private int defaultSize;
    private Paint paint = new Paint();
    private Bitmap sticker;

    public StickerImageView(Context context) {
        super(context);
        init(context, null);
    }

    public StickerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        int padding = UIUtils.dpToPx(context, DEFAULT_PADDING);
        setPadding(padding, padding, padding, padding);
        setBackgroundDrawable(getResources().getDrawable(R.drawable.square_background));

        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        sticker = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sticker);
        defaultSize = UIUtils.dpToPx(getContext(), DEFAULT_SIZE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSelected()) {
            canvas.save();
            canvas.translate(getWidth() - sticker.getWidth(), getHeight() - sticker.getHeight());
            canvas.drawBitmap(sticker, 0, 0, paint);
            canvas.restore();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(defaultSize, defaultSize);
    }
}
