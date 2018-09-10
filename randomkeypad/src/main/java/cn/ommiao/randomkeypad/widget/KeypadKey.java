package cn.ommiao.randomkeypad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.ommiao.randomkeypad.R;
import cn.ommiao.randomkeypad.utils.UIUtils;


/**
 * description:
 * Created by WJD on 2018/1/26.
 */

public class KeypadKey extends View {
    private boolean firstLoad = true;
    private String value = "";
    private int contentType;
    private int textColor;
    private int textSize;
    private Bitmap src;
    private Bitmap bitmap;
    private static final int CONTENT_TYPE_VALUE = 1;
    private static final int CONTENT_TYPE_IMAGE = 2;

    public KeypadKey(Context context) {
        this(context, null);
    }

    public KeypadKey(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeypadKey(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValues(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initValues(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KeypadKey);
        contentType = array.getInt(R.styleable.KeypadKey_contentType, CONTENT_TYPE_VALUE);
        if(firstLoad){
            value = array.getString(R.styleable.KeypadKey_value);
        }
        if (value == null){
            value = "";
        }
        int srcId = array.getResourceId(R.styleable.KeypadKey_src, R.mipmap.aaq);
        textColor = array.getColor(R.styleable.KeypadKey_textColor, Color.BLACK);
        textSize = array.getDimensionPixelSize(R.styleable.KeypadKey_textSize, UIUtils.Companion.dip2px(14));
        src = BitmapFactory.decodeResource(context.getResources(), srcId);
        array.recycle();
    }

    private Paint paint = new Paint();
    Matrix matrix = new Matrix();
    private Rect rect = new Rect();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas.getWidth() == 0 || canvas.getHeight() == 0) {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        paint.setAntiAlias(true);
        if (contentType == CONTENT_TYPE_IMAGE) {
            int iconWidth = src.getWidth();
            int iconHeight = src.getHeight();
            if (bitmap == null){
                float ratioX = (float) iconWidth / width;
                float ratioY = (float) iconHeight / height;
                float ratio = Math.max(ratioX,ratioY);
                ratio = 0.3f / ratio;
                matrix.setScale(ratio, ratio);
                bitmap = Bitmap.createBitmap(src,0,0,iconWidth,iconHeight,matrix,false);
            }
            canvas.drawBitmap(bitmap,(width - bitmap.getWidth()) / 2,(height - bitmap.getHeight()) / 2,paint);
        } else if (contentType == CONTENT_TYPE_VALUE) {
            paint.setColor(textColor);
            paint.setTextSize(textSize);
            paint.setStrokeWidth(textSize >>> 3);
            paint.setTextAlign(Paint.Align.CENTER);
            Paint.FontMetrics fm = paint.getFontMetrics();
            canvas.drawText(value, width / 2, height/2 - (fm.descent - (-fm.ascent + fm.descent)/2), paint);
        }

        super.onDraw(canvas);
    }

    public void setValue(String value){
        this.value = value;
        invalidate();
    }
}
