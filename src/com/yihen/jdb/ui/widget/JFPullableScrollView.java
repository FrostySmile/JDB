/**   
* @Title: JFPullableScrollView.java 
* @Package com.yihen.jdb.ui.widget 
* @Description: TODO
* @author frosty
* @date 2015-5-5 下午2:57:31 
* @version V1.0   
*/
package com.yihen.jdb.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @ClassName: JFPullableScrollView
 * @Description: TODO
 * @author Frosty
 * @date 2015-5-5 下午2:57:31
 *   
 */
public class JFPullableScrollView extends ScrollView implements JFPullable
{

	public JFPullableScrollView(Context context)
	{
		super(context);
	}

	public JFPullableScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public JFPullableScrollView(Context context, AttributeSet attrs, int defStyle)
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

	@Override
	public boolean canPullUp()
	{
		if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
			return true;
		else
			return false;
	}
}