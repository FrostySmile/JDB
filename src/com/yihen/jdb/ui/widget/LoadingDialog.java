package com.yihen.jdb.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.yihen.jdb.R;

/**
 * 加载对话框控件
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class LoadingDialog extends Dialog {

	private Context mContext;
	private LayoutInflater inflater;
	private LayoutParams lp;
	private ImageView img;
	private TextView loadtext;
	private AnimationDrawable ad;

	public LoadingDialog(Context context) {
		super(context, R.style.Dialog);
		this.mContext = context;
		
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.ui_loadingdialog, null);
		img = (ImageView) layout.findViewById(R.id.loading_bar);
		ad = (AnimationDrawable)img.getBackground();
		ad.start();
		loadtext = (TextView) layout.findViewById(R.id.loading_text);
		setContentView(layout);
		
		// 设置window属性
		lp = getWindow().getAttributes();
		lp.gravity = Gravity.CENTER;
		lp.dimAmount = 0.6f; // 去背景遮盖
		lp.alpha = 1.0f;
		getWindow().setAttributes(lp);
	}

	public void setLoadText(String content){
		loadtext.setText(content);
	}

	@Override
	public void show() {
		ad.start();
		super.show();
	}
}