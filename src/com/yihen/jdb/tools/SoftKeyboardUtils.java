package com.yihen.jdb.tools;

import android.content.Context;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class SoftKeyboardUtils {

	/**
	 * 显示软键盘
	 */
	public static void showKeyboard(Context ctx, EditText editText) {
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
		InputMethodManager inputManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(editText, 0);
	}
	
	/**
	 * 隐藏软键盘
	 */
	public static void hideKeyboard(Context ctx, EditText editText) {
		((InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE)).
			hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
	}
	
	/**
	 * 设置光标位置
	 * 
	 * @param editText
	 */
	public static void setCursorPos(EditText editText) {
		String result = editText.getText().toString();
		editText.setSelection(TextUtils.isEmpty(result) ? 0 : result.length());
	}
	
}
