package com.yihen.jdb.ui.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;

import com.yihen.jdb.R;
import com.yihen.jdb.app.AppManager;
import com.yihen.jdb.ui.widget.JFActionSheetMenu;
import com.yihen.jdb.ui.widget.JFActionSheetMenu.OnActionSheetItemClickListener;
import com.yihen.jdb.ui.widget.LoadingDialog;

/**
 * 
 * @author Frosty
 * @date 2015-4-27 下午4:51:58
 * @description  activity 基类，管理旗下得fragment
 *
 */
public class BaseActivity extends FragmentActivity implements OnActionSheetItemClickListener{

	private  LoadingDialog  dialog = null;
	
	/*fragment管理*/
	protected static Map<String,Fragment> fragmentsList  = new HashMap<String, Fragment>();//记录显示得fragment
	protected int currentTabIndex = 0;//纪录下选中的页
	protected Fragment currentFragment = null;//当前显示得fragment
	protected static int tabNum = 0;//模块总个数
	
	/*fragment管理器*/
	private static FragmentManager fm;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		AppManager.getInstance().addActivity(this);
		fm = getSupportFragmentManager();
	}
	
	/**
	 * 添加单一fragment
	 */
	public void addSingleFragment(int layoutId, Fragment fragment) {
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(layoutId, fragment);
		ft.commit();
	}

	/**
	 * 在新事物中加入单一fragment
	 */
	public void newFMaddFragment(int layoutId, Fragment fragment) {
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(layoutId, fragment);
		ft.commit();
	}
	/**
	 * 在新事物中加入单一fragment,带标志tag
	 * @param layoutId
	 * @param fragment
	 * @param tag
	 */
	public void newFMaddFragment(int layoutId, Fragment fragment, String tag) {
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(layoutId, fragment, tag);
		ft.commit();
	}

	/**
	 * 根据tag查找指定Fragment
	 * @param tag
	 * @return
	 */
	public Fragment findFragmentbyTag(String tag) {
		return fm.findFragmentByTag(tag);
	}

	
	/**
	 * 替换fragment
	 */
	public void replaceFragment(int layoutId, Fragment fragment) {
		if (fm.findFragmentById(layoutId) == fragment) {
			return;
		}
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(layoutId, fragment);
		ft.commit();
	}
	public void replaceFragment(int layoutId, Fragment fragment, String tag) {
		if (fm.findFragmentById(layoutId) == fragment) {
			return;
		}
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(layoutId, fragment, tag);
		ft.commit();
	}
	/*
    * 替换fragment 带回滚判断
    * true 可回滚,false 不可回滚
    * */
	public void replaceFragment(int layoutId, Fragment fragment, boolean flag) {
		if (fm.findFragmentById(layoutId) == fragment) {
			return;
		}
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(layoutId, fragment);
		if (flag) {
			ft.addToBackStack(null);
		}
		ft.commit();
	}

	
	
	/**
	 * 移除Fragment
	 *
	 * @param fragment
	 */
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = fm.beginTransaction();
		ft = ft.remove(fragment);
		ft.commit();
	}

	/**
	 * 移除栈顶
	 */
	public void popFragment() {
		fm.popBackStack();
	}
	
	
	/**
	 * 公用返回
	 * @param view
	 */
	public void back(View view){
		this.finish();
	}

	
	/*----------------fragmet 切换和状态保存操作--------------*/
	 /**
	   * 返回当前选中页
	   * @return
	   */
	  public String getCurrentTab() {  
	      return currentTabIndex+"";  
	  }  

	  /**
	   * 取出当前页的fragment
	   * @return
	   */
	  public Fragment getCurrentFragment() {  
	      return fragmentsList.get(currentTabIndex+"");  
	  }  

	  //取出保存状态的fragment
	  public void judgeFragment(Fragment showFragment,int totalTab,int index){
  	 	   getCurrentFragment().onPause(); // 暂停当前tab
  	 	   
	       if (showFragment.isAdded()) {  //如果已经被添加过或者replace过
	      	 	showFragment.onResume(); // 启动目标tab的onResume()  ，而不执行onCreate方法
	       } else { 
	    	   	   FragmentTransaction ft =fm.beginTransaction();
	           ft.add(R.id.fragmentContent, showFragment); 
	           ft.commit();
	       }  
	       showTab(totalTab,index); // 显示目标tab  
	      
	  }

	  /** 
	   * 切换tab 
	   *  
	   * @param idx 
	   */  
	  private void showTab(int totalTab,int index) {  
		  
	      for (int i = 0; i <= totalTab; i++) {  
	          Fragment fragment = fragmentsList.get(i+"");  
	          FragmentTransaction ft =fm.beginTransaction();
	          if(fragment == null){
	        	  	continue;
	          }
	          if (i == index) {  
	              ft.show(fragment);  
	          } else {  
	              ft.hide(fragment);  
	          }  
	          ft.commit();  
	      }  
	      currentTabIndex = index; // 更新目标tab为当前tab  
	  }  

	  /*----------------对话框显示控制----------------*/
	  public  void ShowDialog(Context context,String msg,boolean cancelabel){
		  if(dialog == null){
			  dialog = new LoadingDialog(context);
		  }
		  dialog.setCancelable(cancelabel);
		  if(!TextUtils.isEmpty(msg)){
			  dialog.setLoadText(msg);
		  }else{
			  dialog.setLoadText(context.getString(R.string.watting));
		  }
		  dialog.show();
	  }
	  
	  public void HideDialog(){
		  if(dialog != null){
			  dialog.hide();
		  }
	  }
	  public void DismissDialog(){
		  if(dialog != null){
			  dialog.dismiss();
		  }
	  }
	  public void ChangeDialogMsg(String msg){
		  dialog.setLoadText(msg);
	  }
	  
    /*----------------下滑菜单----------------*/
    
	/*
	* Title: onItemClick
	* Description: 
	* @param view
	* @param itemPosition 
	* @see com.yihen.jdb.ui.widget.JFActionSheetMenu.OnActionSheetItemClickListener#onItemClick(android.view.View, int) 
	*/
	@Override
	public void onItemClick(View view, int itemPosition) {
		// TODO Auto-generated method stub
		
	}

	/*
	* Title: onCanceClick
	* Description: 
	* @param view 
	* @see com.yihen.jdb.ui.widget.JFActionSheetMenu.OnActionSheetItemClickListener#onCanceClick(android.view.View) 
	*/
	@Override
	public void onCanceClick(View view) {
		// TODO Auto-generated method stub
		
	}
    
   public  void showMenu(boolean cancelWhenTouch,String... strings  ){
	   JFActionSheetMenu menu = new JFActionSheetMenu(this);
	   menu.setUseCustonStyle(false);
	   //设置主item
	   menu.setCancelButtonTextAndColor(getString(R.string.cancel), Color.RED);
	   menu.addItems(strings);
		//主item监听
		menu.setItemClickListener(this);
		//取消按钮监听
		menu.setCancelableOnTouchMenuOutside(cancelWhenTouch);
		menu.showMenu();
   }
}
