/**   
* @Title: NoScrollListView.java 
* @Package com.yihen.jdb.ui.widget 
* @Description: TODO
* @author frosty
* @date 2015-5-20 下午2:01:01 
* @version V1.0   
*/
package com.yihen.jdb.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @ClassName: NoScrollListView
 * @Description: 不滚动得listview
 * @author Frosty
 * @date 2015-5-20 下午2:01:01
 *   
 */
public class NoScrollListView extends ListView{

	/**
	 * Title: 
	 * Description: 
	 * @param context
	 * @param attrs
	 */
	public NoScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	/*
	* Title: onMeasure
	* Description: 
	* @param widthMeasureSpec
	* @param heightMeasureSpec 
	* @see android.widget.ListView#onMeasure(int, int) 
	*/
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, expandSpec);  
	}

}
