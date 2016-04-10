package com.ufo.smartedittextsimple.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ufo.smartedittextsimple.R;


/**
 * 作者：XuDiWei
 * <p/>
 * 日期：2015/2/3.20:48
 * <p/>
 * 文件描述：带有计数与输入字符长度限定的EditText
 */
public class SmartEditText extends FrameLayout implements TextWatcher {

    /**
     * 默认最大可输入字符长度
     */
    private static final int DEFAULT_MAXLENGTH = 10;
    private static final int DEFAULT_COLOR = Color.DKGRAY;
    private EditText editText;
    private TextView tvInputCount;
    private String beforText;
    private int maxLength;
    private int cornerTextColor;
    private int textColor;
    private int textSize;
    private OnInputOutLengthListener listener;
    private int hintRes;

    public SmartEditText(Context context) {
        super(context);
        initView();
    }

    public SmartEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SmartEditText);
        maxLength = typedArray.getInteger(R.styleable.SmartEditText_smart_maxLength, DEFAULT_MAXLENGTH);
        cornerTextColor = typedArray.getColor(R.styleable.SmartEditText_smart_cornerTextColor, DEFAULT_COLOR);
        textColor = typedArray.getColor(R.styleable.SmartEditText_smart_textColor, DEFAULT_COLOR);
        textSize = typedArray.getDimensionPixelSize(R.styleable.SmartEditText_smart_textSize, 20);
        hintRes = typedArray.getResourceId(R.styleable.SmartEditText_smart_hint, -1);
        typedArray.recycle();
        initView();
    }

    private void initView() {
        editText = new EditText(getContext());
        editText.setGravity(Gravity.TOP);
        editText.setBackgroundDrawable(this.getBackground());
        editText.setTextColor(textColor);
        editText.setTextSize(textSize);
        if (hintRes != -1) {
            editText.setHint(hintRes);
            editText.setHintTextColor(Color.GRAY);
        }
        editText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        editText.addTextChangedListener(this);
        this.addView(editText);

        tvInputCount = new TextView(getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        layoutParams.bottomMargin = dipTodx(getContext(), 5);
        layoutParams.rightMargin = dipTodx(getContext(), 5);
        tvInputCount.setLayoutParams(layoutParams);
        tvInputCount.setTextColor(cornerTextColor);
        tvInputCount.setText(String.valueOf(0 + "/" + maxLength));
        this.addView(tvInputCount);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforText = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int length = editText.getText().toString().trim().length();
        if (length > maxLength) {
            editText.setText(beforText);
            editText.setSelection(editText.getText().length());
            length = maxLength;
            shakeAnimation(tvInputCount);
            if (null != listener) {
                listener.onInputOutLength(length);
            }
        }
        tvInputCount.setText(String.valueOf(length + "/" + maxLength));
    }

    /**
     * 开始抖动 代码实现
     *
     * @param view
     */
    private void shakeAnimation(View view) {
        TranslateAnimation animation = new TranslateAnimation(0, 10, 0, 0);
        animation.setDuration(500);
        //添加一个循环器。次数是5
        animation.setInterpolator(new CycleInterpolator(10));
        view.startAnimation(animation);
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        tvInputCount.setText(String.valueOf(0 + "/" + this.maxLength));
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setCornerTextColor(int cornerTextColor) {
        this.cornerTextColor = cornerTextColor;
        tvInputCount.setTextColor(this.cornerTextColor);
    }

    public int getCornerTextColor() {
        return this.cornerTextColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        editText.setTextColor(this.textColor);
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        editText.setTextSize(this.textSize);
    }

    private int getTextSize() {
        return this.textSize;
    }

    public void setOnInputOutLengthListener(OnInputOutLengthListener listener) {
        this.listener = listener;
    }

    public int getHintRes() {
        return hintRes;
    }

    public void setHintRes(int hintRes) {
        this.hintRes = hintRes;
        editText.setHint(this.hintRes);
        editText.setHintTextColor(Color.GRAY);
    }

    private static int dipTodx(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dip + 0.5f);
    }


    public interface OnInputOutLengthListener {
        void onInputOutLength(int length);

    }
}
