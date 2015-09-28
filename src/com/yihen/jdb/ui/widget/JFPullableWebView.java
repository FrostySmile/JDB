/**   
* @Title: JFPullableWebView.java 
* @Package com.yihen.jdb.ui.widget 
* @Description: TODO
* @author frosty
* @date 2015-5-5 下午2:56:31 
* @version V1.0   
*/
package com.yihen.jdb.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * @ClassName: JFPullableWebView
 * @Description: TODO
 * @author Frosty
 * @date 2015-5-5 下午2:56:31
 *   
 */
public class JFPullableWebView extends WebView implements JFPullable
{

	public JFPullableWebView(Context context)
	{
		super(context);
	}

	public JFPullableWebView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public JFPullableWebView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canPullDown()
	{
		if (getScrollY() == 0)
			return true;
		else
			return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean canPullUp()
	{
		if (getScrollY() >= getContentHeight() * getScale()
				- getMeasuredHeight())
			return true;
		else
			return false;
	}
}
