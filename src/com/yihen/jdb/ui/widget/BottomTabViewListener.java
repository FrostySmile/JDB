/**   
* @Title: BottomTabViewListener.java 
* @Package com.yihen.jdb.ui.widget 
* @Description: TODO
* @author frosty
* @date 2015-5-4 下午1:51:43 
* @version V1.0   
*/
package com.yihen.jdb.ui.widget;

import android.support.v4.view.ViewPager.OnPageChangeListener;

/**
 * @ClassName: BottomTabViewListener
 * @Description: TODO
 * @author Frosty
 * @date 2015-5-4 下午1:51:43
 *   
 */
public class BottomTabViewListener implements OnPageChangeListener{

	private BottomTabView customRadioGroup;
	private boolean hasListView;//是否结合listview使用
	public BottomTabViewListener(BottomTabView c,boolean hasListView) {
		this.customRadioGroup=c;
		this.hasListView = hasListView;
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
		//arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
		
	}
	public void onPageScrolled(int currentPage, float offsetPercent, int offsetDip){
		if(hasListView){
			return;
		}
		int oldPage = customRadioGroup.getCheckedIndex();
		if (offsetPercent !=0.0f) {
			int right,left;
			if (oldPage == currentPage) {
				// 向右滑动
				left=oldPage;
				right=oldPage+1;
			}else{
				//向左滑动
				left=oldPage-1;
				right=oldPage;
				
			}
			customRadioGroup.itemChangeChecked(left, right, offsetPercent);
		}
	}
	
	
	public void onPageSelected(int arg0){
		customRadioGroup.setCheckedIndex(arg0,true);
	}

	
}
