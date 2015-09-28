package com.yihen.jdb.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.yihen.jdb.R;

/**
 * @author xiaoyuan
 * @email yuanbin19920212@163.com
 * @date 2015/2/15 10:53
 * @description
 */
public class ExpandEditText extends RelativeLayout implements View.OnClickListener {

    private static final int VISIABLE = 0;
    private static final int INVISIABLE = 1;
    private static final int GONE = 2;

    private Context context;

    private String hint = "请输入电话号码";
    private int leftIconVisiable = GONE;
    private int rightIconVisiable = VISIABLE;
    private int background;
    private boolean withProgress = false;
    private int threshold = 2;
    private boolean password = false;
    private boolean enabled = true;
    private AutoCompleteTextView mTextView;
    private ImageView mRightView,mLeftView;
    private ProgressBar mProgressBar;
    private OnEditCompleted onEditCompleted;
    private boolean mCanLoad = true;

    public ExpandEditText(Context context) {
        this(context, null);
    }

    public ExpandEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public ExpandEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpandEditText);
        this.hint = a.getString(R.styleable.ExpandEditText_hint);
        this.leftIconVisiable = a.getInt(R.styleable.ExpandEditText_left_visibility, GONE);
        this.rightIconVisiable = a.getInt(R.styleable.ExpandEditText_right_visibility, VISIABLE);
        this.background = a.getResourceId(R.styleable.ExpandEditText_background, 0);
        this.withProgress = a.getBoolean(R.styleable.ExpandEditText_with_progress, false);
        this.threshold = a.getInt(R.styleable.ExpandEditText_threshold, threshold);
        this.password = a.getBoolean(R.styleable.ExpandEditText_password, false);
        this.enabled = a.getBoolean(R.styleable.ExpandEditText_enabled,true);
        a.recycle();

        init(context);
    }

    private void init(final Context context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.jdb_expand_editext_layout, this);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mRightView = (ImageView) findViewById(R.id.icon_right);
        checkStatusOfIcon(mRightView, rightIconVisiable);
        mLeftView = (ImageView)findViewById(R.id.icon_left);
        checkStatusOfIcon(mLeftView, leftIconVisiable);
        mRightView.setOnClickListener(this);
        mTextView = (AutoCompleteTextView) findViewById(R.id.edittext);
        mTextView.setHint(this.hint);
         mTextView.setFocusableInTouchMode(enabled);
        if(this.password)
        mTextView.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (withProgress) {
                    if (mCanLoad) {
                        if (charSequence.length() >= threshold) {
                            if (onEditCompleted != null)
                                onEditCompleted.startSearch(ExpandEditText.this, charSequence.toString());
                        } else {
                            if (onEditCompleted != null) onEditCompleted.endSearch(ExpandEditText.this);
                        }
                    } else {
                        mCanLoad = !mCanLoad;
                    }
                } else {
                    //有内容显示
                    if (charSequence.length() >= 1)
                        mRightView.setVisibility(View.VISIBLE);
                    else
                        mRightView.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void checkStatusOfIcon(View view, int visiable) {
        if (view == null)
            return;
        switch (visiable) {
            case VISIABLE:
                view.setVisibility(View.VISIBLE);
                break;
            case INVISIABLE:
                view.setVisibility(View.INVISIBLE);
                break;
            case GONE:
                view.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_right:
                mTextView.setText("");
                break;
        }
    }

    /**
     * 设置提示开始字符，只有当withProgress = true时有效。
     *
     * @param size
     */
    public void setThreshold(int size) {
        this.threshold = size;
    }

    public AutoCompleteTextView findEditText() {
        return mTextView;
    }

    /**
     * 获取EditText内容
     *
     * @return
     */
    public Editable getText() {
        return mTextView.getText();
    }

    /**
     * 显示加载
     *
     * @param flag
     */
    public void showProgress(boolean flag) {
        if (flag) {
            mProgressBar.setVisibility(VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    /**
     * 设置EditText内容
     *
     * @param text
     */
    public void setText(String text) {
        if (mTextView != null)
            mTextView.setText(text);
    }

    /**
     * 设置内容,isload 是否提示搜素
     *
     * @param text
     * @param canLoad
     */
    public void setText(String text, boolean canLoad) {
        mCanLoad = canLoad;
        if (mTextView != null)
            mTextView.setText(text);
    }

    /**
     * 设置提示内容
     *
     * @param hint
     */
    public final void setHint(String hint) {
        if (mTextView != null)
            mTextView.setHint(hint);
    }

    /**
     * 设置提示内容
     *
     * @param resid 内容id
     */
    public final void setHint(int resid) {
        if (mTextView != null)
            mTextView.setHint(context.getResources().getString(resid));
    }
    /**
     * 设置键盘搜索监听器
     *
     * @param editorActionListener
     */
    public void setOnEditorActionListener(TextView.OnEditorActionListener editorActionListener) {
        mTextView.setOnEditorActionListener(editorActionListener);
    }

    public void setOnEditCompleted(OnEditCompleted onEditCompleted) {
        this.onEditCompleted = onEditCompleted;
    }

    public interface OnEditCompleted {
        void startSearch(ExpandEditText expandEditText, String text);

        void endSearch(ExpandEditText expandEditText);
    }
}
