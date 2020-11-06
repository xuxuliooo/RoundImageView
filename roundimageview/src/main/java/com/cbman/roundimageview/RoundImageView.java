package com.cbman.roundimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.os.Looper;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Update Date : 2020/8/12.
 * Created by ChenBo on 2017/11/10.
 * Email: chenbohc@163.com
 * QQ: 378277548
 * Description: Custom ImageView.
 * Realize round and rounded rectangle function, Add border line display.
 * Mainly through color rendering, The picture was not cropped
 */
@SuppressWarnings("unused")
public class RoundImageView extends ImageView {

    private static final int LEFT_TOP = 0;
    private static final int LEFT_BOTTOM = 1;
    private static final int RIGHT_TOP = 2;
    private static final int RIGHT_BOTTOM = 3;

    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int MONOSPACE = 3;

    /**
     * Linear gradient extending across the center point..
     */
    private static final int GRADIENT_LINEAR_TYPE = 0;
    /**
     * Radial gradient extending from the center point outward.
     */
    private static final int GRADIENT_RADIAL_TYPE = 1;
    /**
     * Sweep (or angular) gradient sweeping counter-clockwise around the center point.
     */
    private static final int GRADIENT_SWEEP_TYPE = 2;

    /**
     * Constant indicating that no gradient has been set.
     */
    public static final int GRADIENT_NONE = 0;
    /**
     * Use gradient colors for borders.
     */
    public static final int GRADIENT_BORDER = 1;
    /**
     * Use gradients for label text.
     */
    public static final int GRADIENT_LABEL = 2;
    /**
     * Use gradients for label background.
     */
    public static final int GRADIENT_LABEL_BACKGROUND = 3;
    /**
     * Use gradient colors for borders, labels and label backgrounds
     */
    public static final int GRADIENT_ALL = 4;
    /**
     * My paint.
     */
    private Paint mPaint = null;
    /**
     * The Border width.
     */
    private float borderWidth = 2;
    /**
     * The border color state list.
     */
    private ColorStateList mBorderColorStateList;
    /**
     * The text color state list.
     */
    private ColorStateList mTextColorStateList;
    /**
     * The label background color state list.
     */
    private ColorStateList mLabelBackColorStateList;
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
     * Whether to show the label
     */
    private boolean displayLabel = false;

    /**
     * Label text
     */
    private String labelText;
    /**
     * Label text color
     */
    private int textColor = Color.WHITE;
    /**
     * Label text size
     */
    private int textSize = 15;
    /**
     * Label background
     */
    private int labelBackground = Color.parseColor("#9FFF0000");
    /**
     * Label gravity
     */
    private int labelGravity = 2;
    /**
     * Label width
     */
    private int labelWidth = 15;
    /**
     * Distance start location margin
     */
    private int startMargin = 20;

    /**
     * The text paint
     */
    private TextPaint mTextPaint = null;
    /**
     * Text displayed on the label
     */
    private String text;
    /**
     * The gradient color start state color list.
     */
    private ColorStateList mStartColor;
    /**
     * The gradient color center state color list.
     */
    private ColorStateList mCenterColor;
    /**
     * The gradient color end state color list.
     */
    private ColorStateList mEndColor;
    /**
     * The gradient color current start color.
     */
    private int mCurrentStartColor;
    /**
     * The gradient color current center color.
     */
    private int mCurrentCenterColor;
    /**
     * The gradient color current end color.
     */
    private int mCurrentEndColor;
    /**
     * The sRGB colors to be distributed along the gradient line
     */
    private int[] mColors;
    /**
     * May be null. The relative positions [0..1] of
     * each corresponding color in the colors array. If this is null,
     * the the colors are distributed evenly along the gradient line.
     */
    private float[] mPoints;
    /**
     * Use gradient content.
     */
    private int mGradientContent;
    /**
     * Type of gradient. The default type is linear.
     */
    private int mGradientType;
    /**
     * The gradient shader.
     */
    private Shader mGradientShader;
    /**
     * Standard orientation constant.
     * When using a linear gradient, the direction of the gradient.
     */
    private int mOrientation;

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

        /**
         * Gets type.
         *
         * @return the type
         */
        public int getType() {
            return type;
        }
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
        mTextPaint = new TextPaint();

        if (attrs != null) {
            TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.RoundImageView);

            borderWidth = a.getDimension(R.styleable.RoundImageView_borderWidth, borderWidth);
            mBorderColorStateList = a.getColorStateList(R.styleable.RoundImageView_borderColor);
            displayBorder = a.getBoolean(R.styleable.RoundImageView_displayBorder, displayBorder);

            leftTopRadius = a.getDimension(R.styleable.RoundImageView_android_topLeftRadius, leftTopRadius);
            rightTopRadius = a.getDimension(R.styleable.RoundImageView_android_topRightRadius, rightTopRadius);
            leftBottomRadius = a.getDimension(R.styleable.RoundImageView_android_bottomLeftRadius, leftBottomRadius);
            rightBottomRadius = a.getDimension(R.styleable.RoundImageView_android_bottomRightRadius, rightBottomRadius);

            float radius = a.getDimension(R.styleable.RoundImageView_android_radius, 0);
            if (radius > 0)
                leftTopRadius = leftBottomRadius = rightTopRadius = rightBottomRadius = radius;

            int index = a.getInt(R.styleable.RoundImageView_displayType, -1);

            if (index >= 0) {
                displayType = displayTypeArray[index];
            } else {
                displayType = DisplayType.NORMAL;
            }

            displayLabel = a.getBoolean(R.styleable.RoundImageView_displayLabel, displayLabel);
            labelText = a.getString(R.styleable.RoundImageView_android_text);
            mLabelBackColorStateList = a.getColorStateList(R.styleable.RoundImageView_labelBackground);
            textSize = a.getDimensionPixelSize(R.styleable.RoundImageView_android_textSize, textSize);
            mTextColorStateList = a.getColorStateList(R.styleable.RoundImageView_android_textColor);
            labelWidth = a.getDimensionPixelSize(R.styleable.RoundImageView_labelWidth, labelWidth);
            labelGravity = a.getInt(R.styleable.RoundImageView_labelGravity, labelGravity);
            startMargin = a.getDimensionPixelSize(R.styleable.RoundImageView_startMargin, startMargin);

            mGradientType = a.getInt(R.styleable.RoundImageView_android_type, GRADIENT_LINEAR_TYPE);
            mStartColor = a.getColorStateList(R.styleable.RoundImageView_android_startColor);
            mCenterColor = a.getColorStateList(R.styleable.RoundImageView_android_centerColor);
            mEndColor = a.getColorStateList(R.styleable.RoundImageView_android_endColor);
            mGradientContent = a.getInt(R.styleable.RoundImageView_gradientContent, GRADIENT_NONE);
            mOrientation = a.getInt(R.styleable.RoundImageView_android_orientation, LinearLayout.HORIZONTAL);

            final int typefaceIndex = a.getInt(R.styleable.RoundImageView_android_typeface, -1);
            final int styleIndex = a.getInt(R.styleable.RoundImageView_android_textStyle, -1);
            setTypefaceFromAttrs(typefaceIndex, styleIndex);

            text = labelText;
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (displayType == DisplayType.CIRCLE) {
            int measureSpec;
            if (widthSize < heightSize) {
                measureSpec = widthMeasureSpec;
            } else {
                measureSpec = heightMeasureSpec;
            }
            if (widthSize <= 0) {
                measureSpec = heightMeasureSpec;
            }
            super.onMeasure(measureSpec, measureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            resetSize(Math.min(getWidth(), getHeight()) / 2f);
            Bitmap bm = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas mCanvas = new Canvas(bm);
            super.onDraw(mCanvas);
            mPaint.reset();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
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
    private void resetSize(float size) {
        leftTopRadius = Math.min(leftTopRadius, size);
        rightTopRadius = Math.min(rightTopRadius, size);
        leftBottomRadius = Math.min(leftBottomRadius, size);
        rightBottomRadius = Math.min(rightBottomRadius, size);
        borderWidth = Math.min(borderWidth, size / 2);
        labelWidth = (int) Math.min(labelWidth, size / 2);
        textSize = Math.min(textSize, labelWidth);
        startMargin = Math.min(startMargin, (int) (size * 2 - getBevelLineLength()));
    }

    /**
     * @return get Bevel line length
     */
    private float getBevelLineLength() {
        return (float) Math.sqrt(Math.pow(labelWidth, 2) * 2);
    }

    /**
     * Draw my content.
     *
     * @param mCanvas my canvas
     */
    private void drawMyContent(Canvas mCanvas) {
        if (displayType != DisplayType.NORMAL) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            Path path = createPath();
            path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
            mCanvas.drawPath(path, mPaint);
            mPaint.setXfermode(null);
        }
        initGradientShader();
        if (displayBorder) drawBorders(mCanvas);
        if (displayLabel) drawLabels(mCanvas);
    }

    /**
     * Initialize the gradient.
     */
    private void initGradientShader() {
        if (mGradientContent != GRADIENT_NONE) {
            switch (mGradientType) {
                case GRADIENT_LINEAR_TYPE:
                    if (mOrientation == LinearLayout.VERTICAL) {
                        mGradientShader = new LinearGradient(0, 0, 0, getHeight(), mColors, mPoints, Shader.TileMode.CLAMP);
                    } else {
                        mGradientShader = new LinearGradient(0, 0, getWidth(), 0, mColors, mPoints, Shader.TileMode.CLAMP);
                    }
                    break;
                case GRADIENT_RADIAL_TYPE:
                    float radius = (getWidth() > getHeight() ? getWidth() : getHeight()) / 2f;
                    mGradientShader = new RadialGradient(getWidth() / 2f, getHeight() / 2f, radius, mColors, mPoints, Shader.TileMode.CLAMP);
                    break;
                case GRADIENT_SWEEP_TYPE:
                    mGradientShader = new SweepGradient(getWidth() / 2f, getHeight() / 2f, mColors, mPoints);
                    break;
            }
        }
    }

    /**
     * Draw Labels
     *
     * @param mCanvas My canvas
     */
    private void drawLabels(Canvas mCanvas) {
        Path path = new Path();
        Path mTextPath = new Path();
        final float bevelLineLength = getBevelLineLength();

        switch (labelGravity) {
            case LEFT_TOP:
                path.moveTo(startMargin, 0);
                path.rLineTo(bevelLineLength, 0);
                path.lineTo(0, startMargin + bevelLineLength);
                path.rLineTo(0, -bevelLineLength);
                path.close();

                mTextPath.moveTo(0, startMargin + bevelLineLength / 2f);
                mTextPath.lineTo(startMargin + bevelLineLength / 2f, 0);
                break;
            case LEFT_BOTTOM:
                path.moveTo(startMargin, getHeight());
                path.rLineTo(bevelLineLength, 0);
                path.lineTo(0, getHeight() - (startMargin + bevelLineLength));
                path.rLineTo(0, bevelLineLength);
                path.close();

                mTextPath.moveTo(0, getHeight() - (startMargin + bevelLineLength / 2f));
                mTextPath.lineTo(startMargin + bevelLineLength / 2f, getHeight());
                break;
            case RIGHT_TOP:
                path.moveTo(getWidth() - startMargin, 0);
                path.rLineTo(-bevelLineLength, 0);
                path.lineTo(getWidth(), startMargin + bevelLineLength);
                path.rLineTo(0, -bevelLineLength);
                path.close();

                mTextPath.moveTo(getWidth() - (startMargin + bevelLineLength / 2f), 0);
                mTextPath.lineTo(getWidth(), startMargin + bevelLineLength / 2f);
                break;
            case RIGHT_BOTTOM:
                path.moveTo(getWidth() - startMargin, getHeight());
                path.rLineTo(-bevelLineLength, 0);
                path.lineTo(getWidth(), getHeight() - (startMargin + bevelLineLength));
                path.rLineTo(0, bevelLineLength);
                path.close();

                mTextPath.moveTo(getWidth() - (startMargin + bevelLineLength / 2f), getHeight());
                mTextPath.lineTo(getWidth(), getHeight() - (startMargin + bevelLineLength / 2f));
                break;
        }
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        if (mGradientContent == GRADIENT_LABEL_BACKGROUND || mGradientContent == GRADIENT_ALL) {
            mTextPaint.setShader(mGradientShader);
        } else {
            mTextPaint.setShader(null);
            mTextPaint.setColor(labelBackground);
        }
        mCanvas.drawPath(path, mTextPaint);

        mTextPaint.setTextSize(textSize);
        if (mGradientContent == GRADIENT_LABEL) {
            mTextPaint.setShader(mGradientShader);
        } else {
            mTextPaint.setShader(null);
            mTextPaint.setColor(textColor);
        }
        if (null == text) text = "";
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        float textWidth = mTextPaint.measureText(text);
        PathMeasure pathMeasure = new PathMeasure(mTextPath, false);
        float pathLength = pathMeasure.getLength();
        if (textWidth > pathLength) {//Text length is greater than the length of the drawing area, the text content is cropped. Replace with ellipsis
            float sl = textWidth / text.length();
            float le = textWidth - pathLength;
            int num = (int) Math.floor(le / sl);
            text = text.substring(0, text.length() - (num + 2)) + "...";
        }
        Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();
        float v = (fm.bottom - fm.top) / 2f - fm.bottom;
        mCanvas.drawTextOnPath(text, mTextPath, 0, v, mTextPaint);
    }

    /**
     * Draw borders.
     *
     * @param mCanvas my canvas
     */
    private void drawBorders(Canvas mCanvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(borderWidth);
        if (mGradientContent == GRADIENT_ALL || mGradientContent == GRADIENT_BORDER) {
            mPaint.setShader(mGradientShader);
        } else {
            mPaint.setShader(null);
            mPaint.setColor(borderColor);
        }
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
                mPath.addCircle(getWidth() / 2f, getHeight() / 2f, getWidth() / 2f - size, Path.Direction.CW);
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
        if (this.borderWidth != borderWidth) {
            this.borderWidth = borderWidth;
            if (displayBorder)
                postInvalidate();
        }
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
        if (this.borderColor != borderColor) {
            this.borderColor = borderColor;
            if (displayBorder)
                postInvalidate();
        }
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
        if (this.displayBorder != displayBorder) {
            this.displayBorder = displayBorder;
            postInvalidate();
        }
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
        if (this.leftTopRadius != leftTopRadius) {
            this.leftTopRadius = leftTopRadius;
            if (displayType != DisplayType.NORMAL)
                postInvalidate();
        }
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
        if (this.rightTopRadius != rightTopRadius) {
            this.rightTopRadius = rightTopRadius;
            if (displayType != DisplayType.NORMAL)
                postInvalidate();
        }
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
        if (this.leftBottomRadius != leftBottomRadius) {
            this.leftBottomRadius = leftBottomRadius;
            if (displayType != DisplayType.NORMAL)
                postInvalidate();
        }
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
        if (this.rightBottomRadius != rightBottomRadius) {
            this.rightBottomRadius = rightBottomRadius;
            if (displayType != DisplayType.NORMAL)
                postInvalidate();
        }
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
        if (this.leftTopRadius == leftTopRadius
                && this.rightTopRadius == rightTopRadius
                && this.leftBottomRadius == leftBottomRadius
                && this.rightBottomRadius == rightBottomRadius)
            return;

        this.leftTopRadius = leftTopRadius;
        this.rightTopRadius = rightTopRadius;
        this.leftBottomRadius = leftBottomRadius;
        this.rightBottomRadius = rightBottomRadius;
        if (displayType != DisplayType.NORMAL)
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
        if (this.displayType != displayType) {
            this.displayType = displayType;
            if (displayLabel)
                postInvalidate();
        }
    }

    /**
     * Sets display label.
     *
     * @param displayLabel the display label
     */
    public void setDisplayLabel(boolean displayLabel) {
        if (this.displayLabel != displayLabel) {
            this.displayLabel = displayLabel;
            if (displayLabel)
                postInvalidate();
        }
    }

    /**
     * Sets label text.
     *
     * @param labelText The label text
     */
    public void setLabelText(String labelText) {
        if (!TextUtils.equals(this.labelText, labelText)) {
            this.labelText = labelText;
            text = labelText;
            if (displayLabel)
                postInvalidate();
        }
    }

    /**
     * Sets text color.
     *
     * @param textColor the text color
     */
    public void setTextColor(int textColor) {
        if (this.textColor != textColor) {
            this.textColor = textColor;
            if (displayLabel)
                postInvalidate();
        }
    }

    /**
     * Sets text color.
     *
     * @param mTextColorStateList the text color state list
     */
    public void setTextColor(ColorStateList mTextColorStateList) {
        this.mTextColorStateList = mTextColorStateList;
        if (displayLabel) {
            invalidate();
        }
    }

    /**
     * Sets text size.
     *
     * @param textSize the text size
     */
    public void setTextSize(int textSize) {
        if (this.textSize != textSize) {
            this.textSize = textSize;
            if (displayLabel)
                postInvalidate();
        }
    }

    /**
     * Sets label background.
     *
     * @param labelBackground the label background
     */
    public void setLabelBackground(int labelBackground) {
        if (this.labelBackground != labelBackground) {
            this.labelBackground = labelBackground;
            if (displayLabel)
                postInvalidate();
        }
    }

    /**
     * Sets label background.
     *
     * @param mLabelBackColorStateList the label background color state list
     */
    public void setLabelBackground(ColorStateList mLabelBackColorStateList) {
        this.mLabelBackColorStateList = mLabelBackColorStateList;
        if (displayLabel) {
            invalidate();
        }
    }

    /**
     * Sets label gravity.
     *
     * @param labelGravity the label gravity
     */
    public void setLabelGravity(int labelGravity) {
        if (this.labelGravity != labelGravity) {
            this.labelGravity = labelGravity;
            if (displayLabel)
                postInvalidate();
        }
    }

    /**
     * Sets label width.
     *
     * @param labelWidth the label width
     */
    public void setLabelWidth(int labelWidth) {
        if (this.labelWidth != labelWidth) {
            this.labelWidth = labelWidth;
            if (displayLabel)
                postInvalidate();
        }
    }

    /**
     * Sets start margin.
     *
     * @param startMargin the start margin
     */
    public void setStartMargin(int startMargin) {
        if (this.startMargin != startMargin) {
            this.startMargin = startMargin;
            if (displayLabel)
                postInvalidate();
        }
    }

    /**
     * Is display label boolean.
     *
     * @return the boolean
     */
    public boolean isDisplayLabel() {
        return displayLabel;
    }

    /**
     * Gets label text.
     *
     * @return the label text
     */
    public String getLabelText() {
        return labelText;
    }

    /**
     * Gets text color.
     *
     * @return the text color
     */
    public int getTextColor() {
        return textColor;
    }

    /**
     * Gets text size.
     *
     * @return the text size
     */
    public int getTextSize() {
        return textSize;
    }

    /**
     * Gets label background.
     *
     * @return the label background
     */
    public int getLabelBackground() {
        return labelBackground;
    }

    /**
     * Gets label gravity.
     *
     * @return the label gravity
     */
    public int getLabelGravity() {
        return labelGravity;
    }

    /**
     * Gets label width.
     *
     * @return the label width
     */
    public int getLabelWidth() {
        return labelWidth;
    }

    /**
     * Gets start margin.
     *
     * @return the start margin
     */
    public int getStartMargin() {
        return startMargin;
    }

    private void setTypefaceFromAttrs(int typefaceIndex, int styleIndex) {
        Typeface tf = null;
        switch (typefaceIndex) {
            case SANS:
                tf = Typeface.SANS_SERIF;
                break;

            case SERIF:
                tf = Typeface.SERIF;
                break;

            case MONOSPACE:
                tf = Typeface.MONOSPACE;
                break;
        }

        setTypeface(tf, styleIndex);
    }

    /**
     * Sets the typeface and style in which the text should be displayed,
     * and turns on the fake bold and italic bits in the Paint if the
     * Typeface that you provided does not have all the bits in the
     * style that you specified.
     *
     * @param tf    the Typeface
     * @param style the style
     */
    public void setTypeface(Typeface tf, int style) {
        if (style > 0) {
            if (tf == null) {
                tf = Typeface.defaultFromStyle(style);
            } else {
                tf = Typeface.create(tf, style);
            }

            setTypeface(tf);
            // now compute what (if any) algorithmic styling is needed
            int typefaceStyle = tf != null ? tf.getStyle() : 0;
            int need = style & ~typefaceStyle;
            mTextPaint.setFakeBoldText((need & Typeface.BOLD) != 0);
            mTextPaint.setTextSkewX((need & Typeface.ITALIC) != 0 ? -0.25f : 0);
        } else {
            mTextPaint.setFakeBoldText(false);
            mTextPaint.setTextSkewX(0);
            setTypeface(tf);
        }
    }

    /**
     * Sets the typeface and style in which the text should be displayed.
     * Note that not all Typeface families actually have bold and italic
     * variants, so you may need to use
     * {@link #setTypeface(Typeface, int)} to get the appearance
     * that you actually want.
     *
     * @param tf the tf
     * @see #getTypeface()
     */
    public void setTypeface(Typeface tf) {
        if (mTextPaint.getTypeface() != tf) {
            mTextPaint.setTypeface(tf);
            if (displayLabel)
                postInvalidate();
        }
    }


    /**
     * Gets the current {@link Typeface} that is used to style the text.
     *
     * @return The current Typeface.
     * @see #setTypeface(Typeface)
     */
    public Typeface getTypeface() {
        return mTextPaint.getTypeface();
    }

    /**
     * Sets gradient type.
     *
     * @param gradientType the gradient type
     */
    public void setGradientType(int gradientType) {
        if (mGradientType != gradientType) {
            mGradientType = gradientType;
            invalidate();
        }
    }

    /**
     * Sets gradient content.
     *
     * @param gradientContent the gradient content
     *                        {@link RoundImageView#GRADIENT_NONE,
     * @link RoundImageView#GRADIENT_BORDER,
     * @link RoundImageView#GRADIENT_LABEL,
     * @link RoundImageView#GRADIENT_LABEL_BACKGROUND,
     * @link RoundImageView#GRADIENT_ALL}
     */
    public void setGradientContent(int gradientContent) {
        if (mGradientContent != gradientContent) {
            mGradientContent = gradientContent;
            invalidate();
        }
    }

    /**
     * Sets orientation.
     *
     * @param mOrientation the linear shader orientation
     */
    public void setOrientation(int mOrientation) {
        if (this.mOrientation != mOrientation) {
            this.mOrientation = mOrientation;
            invalidate();
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        updateColorState();
    }

    /**
     * Update color state.
     */
    public void updateColorState() {
        boolean invalid = false;
        final int[] drawableState = getDrawableState();
        if (mBorderColorStateList != null) {
            int borderColor = mBorderColorStateList.getColorForState(drawableState, 0);
            if (this.borderColor != borderColor) {
                this.borderColor = borderColor;
                invalid = true;
            }
        }
        if (mLabelBackColorStateList != null) {
            int labelBack = mLabelBackColorStateList.getColorForState(drawableState, 0);
            if (labelBack != labelBackground) {
                labelBackground = labelBack;
                invalid = true;
            }
        }
        if (mTextColorStateList != null) {
            int textColor = mTextColorStateList.getColorForState(drawableState, 0);
            if (this.textColor != textColor) {
                this.textColor = textColor;
                invalid = true;
            }
        }
        if (mStartColor != null) {
            int startColor = mStartColor.getColorForState(drawableState, 0);
            if (startColor != mCurrentStartColor) {
                mCurrentStartColor = startColor;
                invalid = true;
            }
        }
        if (mCenterColor != null) {
            int centerColor = mCenterColor.getColorForState(drawableState, 0);
            if (centerColor != mCurrentCenterColor) {
                mCurrentCenterColor = centerColor;
                invalid = true;
            }
        }
        if (mEndColor != null) {
            int endColor = mEndColor.getColorForState(drawableState, 0);
            if (endColor != mCurrentEndColor) {
                mCurrentEndColor = endColor;
                invalid = true;
            }
        }
        if (invalid) {
            if (mStartColor != null && mCenterColor != null && mEndColor != null) {
                mColors = new int[]{mCurrentStartColor, mCurrentCenterColor, mCurrentEndColor};
                mPoints = new float[]{0.0f, 0.5f, 1.0f};
            } else if (mStartColor != null && mEndColor != null) {
                mColors = new int[]{mCurrentStartColor, mCurrentEndColor};
                mPoints = new float[]{0.0f, 1.0f};
            } else if (mStartColor != null && mCenterColor != null) {
                mColors = new int[]{mCurrentStartColor, mCurrentCenterColor};
                mPoints = new float[]{0.0f, 0.5f};
            } else if (mCenterColor != null && mEndColor != null) {
                mColors = new int[]{mCurrentCenterColor, mCurrentEndColor};
                mPoints = new float[]{0.5f, 1.0f};
            }
            invalidate();
        }
    }

    @Override
    public void invalidate() {
        postInvalidate();
    }

    @Override
    public void postInvalidate() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            super.postInvalidate();
        } else {
            super.invalidate();
        }
    }
}