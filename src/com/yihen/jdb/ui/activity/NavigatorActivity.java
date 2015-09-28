/**   
* @Title: NavigatorActivity.java 
* @Package com.yihen.jdb.ui.activity 
* @Description: TODO
* @author frosty
* @date 2015-6-11 下午4:35:13 
* @version V1.0   
*/
package com.yihen.jdb.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yihen.jdb.R;
import com.yihen.jdb.app.AppConfig;
import com.yihen.jdb.app.AppConstants;
import com.yihen.jdb.app.AppManager;
import com.yihen.jdb.ui.adapter.NavigatorPageAdapter;
import com.yihen.jdb.ui.widget.CirclePageIndicator;

/**
 * @ClassName: NavigatorActivity
 * @Description: 新用户引导页
 * @author Frosty
 * @date 2015-6-11 下午4:35:13
 *   
 */
@ContentView(R.layout.activity_navigator)
public class NavigatorActivity extends BaseActivity implements OnTouchListener{

	@ViewInject(R.id.pager_navigator) ViewPager pager_navigator;
	@ViewInject(R.id.pager_Indicator) CirclePageIndicator pager_Indicator;
	@ViewInject(R.id.navi_btn) TextView navi_btn;
	private int[] navigatorId = {R.drawable.btn_label_green,R.drawable.btn_label_orange,R.drawable.btn_label_red};
	private NavigatorPageAdapter adapter;
	private int flaggingWidth;
	private int size = 0;
	private int lastX = 0;
	private int currentIndex = 0;
	private boolean locker = true;
	 List<View> navigatorViewList = null;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ViewUtils.inject(this);
		saveCurrentVersionCode();
		init();
	}
	
	
	/**
	 * 
	 * @description  新版本安装，保存版本号
	 * @return void
	 */
	private void saveCurrentVersionCode(){
		//保存新的版本号到私有文件中
		SharedPreferences sp = AppConfig.getInstance(this).getSharedPrefWithName(AppConstants.DBSHAREDFILE);
		Editor editor = sp.edit();
		editor.putLong(AppConstants.APP_VERSION_CODE, AppManager.getInstance().getCurrentVersionCode(this));
		editor.apply();
	}
	
	/**
	 * 
	 * @description  
	 * @return void
	 */
	private void init(){
		DisplayMetrics dm = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        flaggingWidth = dm.widthPixels / 4;
        navigatorViewList = new ArrayList<View>();
        for(int i = 0; i<navigatorId.length; i++){
        	View view = LayoutInflater.from(this).inflate(
					R.layout.navigator_pagerview, null);
			ImageView nv_img = (ImageView) view.findViewById(R.id.nv_img);
			nv_img.setImageResource(navigatorId[i]);
			navigatorViewList.add(view);
        }
        size = navigatorViewList.size();
        
        adapter = new NavigatorPageAdapter(this, navigatorViewList);
        pager_navigator.setAdapter(adapter);
        pager_Indicator.setmListener(new MypageChangeListener());
        pager_Indicator.setViewPager(pager_navigator);
		if (navigatorViewList.size() == 1) {
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					gotoWelcome();
				}
			}, 1000);
		} else {
			pager_navigator.setOnTouchListener(this);
		}

	}
	
	@OnClick(R.id.navi_btn)
	public void goToWelcoemByBtn(View view){
		gotoWelcome();
	}
	
	private class MypageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int position) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			currentIndex = arg0;
			if(currentIndex==navigatorViewList.size()-1){
				navi_btn.setText(R.string.experienceNow);
			}else{
				navi_btn.setText(R.string.overLook);
			}
		}

	}
	
	private void gotoWelcome(){
		Intent intent = new Intent(NavigatorActivity.this,WelcomeActivity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.alpha_in_anim,R.anim.alpha_out_anim);
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = (int)event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			if((lastX - event.getX()) > flaggingWidth && (currentIndex == size -1) && locker){
				locker = false;
				System.err.println("-------1111-------");
				gotoWelcome();
			}
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
