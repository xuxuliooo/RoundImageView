package com.cbman.roundimageview;

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
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by ChenBo on 2017/11/10.
 * Email: chenbohc@163.com
 * QQ: 378277548
 * Description: Custom ImageView.
 * Realize round and rounded rectangle function, Add border line display.
 * Mainly through color rendering, The picture was not cropped
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
    private int borderColor = Color.parseColor("#8A2BE2");
    /**
     * The Display border.
     */
    private boolean displayBorder;
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
        setLayerType(LAYER_TYPE_HARDWARE, null);
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

            float radius = typedArray.getDimension(R.styleable.RoundImageView_radius, 0);
            if (radius > 0)
                leftTopRadius = leftBottomRadius = rightTopRadius = rightBottomRadius = radius;

            int index = typedArray.getInt(R.styleable.RoundImageView_displayType, -1);

            if (index >= 0) {
                displayType = displayTypeArray[index];
            } else {
                displayType = DisplayType.NORMAL;
            }
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,
                displayType == DisplayType.CIRCLE ? widthMeasureSpec : heightMeasureSpec);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            resetSize(Math.min(getWidth(), getHeight()) / 2);
            Bitmap bm = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas mCanvas = new Canvas(bm);
            super.onDraw(mCanvas);
            mPaint.reset();
            mPaint.setAntiAlias(true);
            drawMyContent(mCanvas);
            canvas.drawBitmap(bm, 0, 0, mPaint);
            bm.recycle();
        } else {
            super.onDraw(canvas);
        }
    }

    /**
     * Reset Size.
     *
     * @param size the size
     */
    private void resetSize(int size) {
        leftTopRadius = Math.min(leftTopRadius, size);
        rightTopRadius = Math.min(rightTopRadius, size);
        leftBottomRadius = Math.min(leftBottomRadius, size);
        rightBottomRadius = Math.min(rightBottomRadius, size);
        borderWidth = Math.min(borderWidth, size / 2);
    }

    /**
     * Draw my content.
     *
     * @param mCanvas my canvas
     */
    private void drawMyContent(Canvas mCanvas) {

        if (displayType != DisplayType.NORMAL) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            Path path = createPath();
            path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
            mCanvas.drawPath(path, mPaint);
            mPaint.setXfermode(null);
        }

        if (displayBorder) drawBorders(mCanvas);
    }

    /**
     * Draw borders.
     *
     * @param mCanvas my canvas
     */
    private void drawBorders(Canvas mCanvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(borderColor);
        mPaint.setStrokeWidth(borderWidth);
        Path path = createPath();
        mCanvas.drawPath(path, mPaint);
    }

    /**
     * Create path.
     *
     * @return the path
     */
    private Path createPath() {
        Path mPath = new Path();
        float size = borderWidth / 2;
        switch (displayType) {
            case CIRCLE:
                mPath.addCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - size, Path.Direction.CW);
                break;
            case ROUND_RECT:
                RectF rectF = new RectF(0, 0, getWidth(), getHeight());
                rectF.inset(size, size);
                mPath.addRoundRect(rectF,
                        new float[]{
                                leftTopRadius, leftTopRadius,
                                rightTopRadius, rightTopRadius,
                                rightBottomRadius, rightBottomRadius,
                                leftBottomRadius, leftBottomRadius},
                        Path.Direction.CW);
                break;
            default:
                RectF rect = new RectF(0, 0, getWidth(), getHeight());
                rect.inset(size, size);
                mPath.addRect(rect, Path.Direction.CW);
                break;
        }
        return mPath;
    }

    /**
     * Gets border width.
     *
     * @return the border width
     */
    public float getBorderWidth() {
        return borderWidth;
    }

    /**
     * Sets border width.
     *
     * @param borderWidth the border width
     */
    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        postInvalidate();
    }

    /**
     * Gets border color.
     *
     * @return the border color
     */
    public int getBorderColor() {
        return borderColor;
    }

    /**
     * Sets border color.
     *
     * @param borderColor the border color
     */
    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        postInvalidate();
    }

    /**
     * Is display border boolean.
     *
     * @return the boolean
     */
    public boolean isDisplayBorder() {
        return displayBorder;
    }

    /**
     * Sets display border.
     *
     * @param displayBorder the display border
     */
    public void setDisplayBorder(boolean displayBorder) {
        this.displayBorder = displayBorder;
        postInvalidate();
    }

    /**
     * Gets left top radius.
     *
     * @return the left top radius
     */
    public float getLeftTopRadius() {
        return leftTopRadius;
    }

    /**
     * Sets left top radius.
     *
     * @param leftTopRadius the left top radius
     */
    public void setLeftTopRadius(float leftTopRadius) {
        this.leftTopRadius = leftTopRadius;
        postInvalidate();
    }

    /**
     * Gets right top radius.
     *
     * @return the right top radius
     */
    public float getRightTopRadius() {
        return rightTopRadius;
    }

    /**
     * Sets right top radius.
     *
     * @param rightTopRadius the right top radius
     */
    public void setRightTopRadius(float rightTopRadius) {
        this.rightTopRadius = rightTopRadius;
        postInvalidate();
    }

    /**
     * Gets left bottom radius.
     *
     * @return the left bottom radius
     */
    public float getLeftBottomRadius() {
        return leftBottomRadius;
    }

    /**
     * Sets left bottom radius.
     *
     * @param leftBottomRadius the left bottom radius
     */
    public void setLeftBottomRadius(float leftBottomRadius) {
        this.leftBottomRadius = leftBottomRadius;
        postInvalidate();
    }

    /**
     * Gets right bottom radius.
     *
     * @return the right bottom radius
     */
    public float getRightBottomRadius() {
        return rightBottomRadius;
    }

    /**
     * Sets right bottom radius.
     *
     * @param rightBottomRadius the right bottom radius
     */
    public void setRightBottomRadius(float rightBottomRadius) {
        this.rightBottomRadius = rightBottomRadius;
        postInvalidate();
    }

    /**
     * Sets radius.
     *
     * @param leftTopRadius     the left top radius
     * @param rightTopRadius    the right top radius
     * @param leftBottomRadius  the left bottom radius
     * @param rightBottomRadius the right bottom radius
     */
    public void setRadius(float leftTopRadius, float rightTopRadius, float leftBottomRadius, float rightBottomRadius) {
        this.leftTopRadius = leftTopRadius;
        this.rightTopRadius = rightTopRadius;
        this.leftBottomRadius = leftBottomRadius;
        this.rightBottomRadius = rightBottomRadius;
        postInvalidate();
    }

    /**
     * Sets radius.
     *
     * @param radius the radius
     */
    public void setRadius(float radius) {
        setRadius(radius, radius, radius, radius);
    }

    /**
     * Gets display type.
     *
     * @return the display type
     */
    public DisplayType getDisplayType() {
        return displayType;
    }

    /**
     * Sets display type.
     *
     * @param displayType the display type
     */
    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
        postInvalidate();
    }
}
