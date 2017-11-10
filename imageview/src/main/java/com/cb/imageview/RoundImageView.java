package com.cb.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Chub on 2017/11/10.
 * Email: chenbohc@163.com
 * QQ: 378277548
 * Description: 自定义ImageView，实现圆形和圆角矩形功能，并添加边框线显示。主要通过颜色渲染实现，并未对图片进行裁剪
 */
public class RoundImageView extends AppCompatImageView {

    /**
     * My paint.
     */
    private Paint mPaint;
    /**
     * The Border width.
     */
    private float borderWidth = 2;
    /**
     * The Border color.
     */
    private int borderColor = Color.WHITE;
    /**
     * The Display border.
     */
    private boolean displayBorder = true;
    /**
     * The Left top radius.
     */
    private float leftTopRadius,
    /**
     * The Right top radius.
     */
    rightTopRadius,
    /**
     * The Left bottom radius.
     */
    leftBottomRadius,
    /**
     * The Right bottom radius.
     */
    rightBottomRadius;

    /**
     * The Display type.
     */
    private DisplayType displayType;
    /**
     * My path.
     */
    private Path mPath;

    /**
     * The enum Display type.
     */
    public enum DisplayType {
        /**
         * Normal display type.
         */
        NORMAL(0),
        /**
         * Circle display type.
         */
        CIRCLE(1),
        /**
         * Round rect display type.
         */
        ROUND_RECT(2);

        DisplayType(int type) {
            this.type = type;
        }

        /**
         * The Type.
         */
        final int type;
    }

    /**
     * The Display type array.
     */
    private static final DisplayType[] displayTypeArray = {
            DisplayType.NORMAL,
            DisplayType.CIRCLE,
            DisplayType.ROUND_RECT
    };

    /**
     * Instantiates a new Round image view.
     *
     * @param context the Context
     */
    public RoundImageView(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Round image view.
     *
     * @param context the Context
     * @param attrs   the AttributeSet
     */
    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a new Round image view.
     *
     * @param context      the Context
     * @param attrs        the AttributeSet
     * @param defStyleAttr the default style Attribute
     */
    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * Init.
     *
     * @param ctx   the Context
     * @param attrs the AttributeSet
     */
    private void init(Context ctx, AttributeSet attrs) {
        mPaint = new Paint();

        if (attrs != null) {
            TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.RoundImageView);

            borderWidth = typedArray.getDimension(R.styleable.RoundImageView_borderWidth, borderWidth);
            borderColor = typedArray.getColor(R.styleable.RoundImageView_borderColor, borderColor);
            displayBorder = typedArray.getBoolean(R.styleable.RoundImageView_displayBorder, displayBorder);

            leftTopRadius = typedArray.getDimension(R.styleable.RoundImageView_leftTopRadius, leftTopRadius);
            rightTopRadius = typedArray.getDimension(R.styleable.RoundImageView_rightTopRadius, rightTopRadius);
            leftBottomRadius = typedArray.getDimension(R.styleable.RoundImageView_leftBottomRadius, leftBottomRadius);
            rightBottomRadius = typedArray.getDimension(R.styleable.RoundImageView_rightBottomRadius, rightBottomRadius);

            int index = typedArray.getInt(R.styleable.RoundImageView_displayType, -1);

            if (index >= 0) {
                displayType = displayTypeArray[index];
            } else {
                displayType = DisplayType.CIRCLE;
            }

            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bm = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(bm);
        super.onDraw(mCanvas);

        mPaint.setAntiAlias(true);
        drawMyContent(mCanvas);

        canvas.drawBitmap(bm, 0, 0, mPaint);
        bm.recycle();
    }

    /**
     * Draw my content.
     *
     * @param mCanvas my canvas
     */
    private void drawMyContent(Canvas mCanvas) {
        switch (displayType) {
            case CIRCLE:
                drawCutCircle(mCanvas);
                break;
            case ROUND_RECT:
                drawCutRoundRect(mCanvas);
                break;
        }

        if (displayBorder) drawBorders(mCanvas);
    }

    /**
     * Draw cut circle.
     *
     * @param mCanvas my canvas
     */
    private void drawCutCircle(Canvas mCanvas) {
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mPath = new Path();
        mPath.addCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, Path.Direction.CW);
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        mCanvas.drawPath(mPath, mPaint);
        mPaint.setXfermode(null);
    }

    /**
     * Draw cut round rect.
     *
     * @param mCanvas my canvas
     */
    public void drawCutRoundRect(Canvas mCanvas) {

    }

    /**
     * Draw borders.
     *
     * @param mCanvas my canvas
     */
    private void drawBorders(Canvas mCanvas) {

    }
}
