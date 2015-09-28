package com.yihen.jdb.ui.activity;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nineoldandroids.view.ViewHelper;
import com.yihen.jdb.R;
import com.yihen.jdb.app.AppManager;
import com.yihen.jdb.tools.GetDataFromRes;
import com.yihen.jdb.ui.adapter.MenuAdapter;
import com.yihen.jdb.ui.fragment.Fragment_Attention;
import com.yihen.jdb.ui.fragment.Fragment_Me;
import com.yihen.jdb.ui.fragment.Fragment_Discover;
import com.yihen.jdb.ui.widget.BottomTabView;
import com.yihen.jdb.ui.widget.BottomTabView.OnItemSelectedListener;
import com.yihen.jdb.ui.widget.SlidingPageMenu;
import com.yihen.jdb.ui.widget.SlidingPageMenu.ChangeMenuStateListener;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements ChangeMenuStateListener{

	/*消息状态*/
	private static final int USERINFO_COMPLETE = 100;//获取用户信息完成
	
	/*控件*/
	@ViewInject(R.id.img_header) ImageView img_header;//主页顶部用户头像
	@ViewInject(R.id.img_menu) ImageView img_menu;//主页顶部菜单按钮
	@ViewInject(R.id.slidPageMenu) SlidingPageMenu slidPageMenu;//主控件
	@ViewInject(R.id.menu_listview) ListView menuListView;//侧滑菜单
	@ViewInject(R.id.footerTab) BottomTabView footerTab;//模块底部导航)
	@ViewInject(R.id.bg_content) ImageView bg_content;//内容部分背景遮盖层
	@ViewInject(R.id.item_content) LinearLayout item_content;//内容布局
	/*Tab底部导航Icon,背景*/
	private int[] unSelectedIcon={R.drawable.main_footer_contanct,R.drawable.main_footer_discovery,R.drawable.main_footer_me};
	private int[] SelectedIcon={R.drawable.main_footer_contanct_selected,R.drawable.main_footer_discovery_selected,R.drawable.main_footer_me_selected};

//	private int[] unSelectedBg = {R.drawable.tab_bg_normal,R.drawable.tab_bg_normal,R.drawable.tab_bg_normal,R.drawable.tab_bg_normal};
//	private int[] SelectedBg= {R.drawable.tab_bg_pressed,R.drawable.tab_bg_pressed,R.drawable.tab_bg_pressed,R.drawable.tab_bg_pressed};
	/*Menu侧滑菜单项item图标和标题*/
	private int[] menuItemIcon = {R.drawable.menu_icon_1,R.drawable.menu_icon_2,R.drawable.menu_icon_3};
  
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		/*
		* Title: handleMessage
		* Description: Thread data back 
		* @param msg 
		*/
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case USERINFO_COMPLETE:	
				break;
				
			}
		}
		
		
	};
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        String[] tabItemText = GetDataFromRes.getStrArray(this, R.array.FootTab);
        String[] menuItemTitle = GetDataFromRes.getStrArray(this, R.array.MenuItems);
        //菜单数据
        List<Map<String,Object>> menuItems = GetDataFromRes.convertArr2ListMap(new String[]{"menuIcon","menuTitle"}, menuItemIcon, menuItemTitle);
        //设置顶部显示
//        showHeaderIcon(true);
        //初始化菜单
        initMenuItems(menuItems);
        //初始化导航
        initFooterTab(tabItemText);
        
    }


    /**
     * 初始化menu菜单项
     */
    private void initMenuItems(List<Map<String,Object>> menuItems){
    		MenuAdapter menuAdapter = new MenuAdapter(this, menuItems);
    		menuListView.setAdapter(menuAdapter);
    }
    
    /**
	 * 初始化content底部模块导航
	 */
	private void initFooterTab(String[] tabItemText){
		final int totalTab = tabItemText.length;
		for (int i = 0; i < unSelectedIcon.length; i++) {
			footerTab.addItem(unSelectedIcon[i],SelectedIcon[i],tabItemText[i],0,0);
		}
		footerTab.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(int position) {
				/*每次新建一个根Fragment*/
				createFragment(totalTab,position);
			}
		});
		footerTab.setCheckedIndex(0,true);
	}

	
	/**
	 * 替换主界面fragment
	 * @param 选中模块
	 */
	private void createFragment(int totalTab,int selecTab){
		currentFragment = null;
		if(fragmentsList != null && fragmentsList.size()>0 ){
			currentFragment = fragmentsList.get(selecTab+"");
		}
		if(selecTab==0){
			img_header.setVisibility(View.VISIBLE);
			img_menu.setVisibility(View.GONE);
		}else{
			img_menu.setVisibility(View.VISIBLE);
			img_header.setVisibility(View.GONE);
		}
		if(currentFragment == null){
			Fragment fg = null;
			if(selecTab == 0){
				fg = new Fragment_Attention();
			}
			else if(selecTab == 1){
				fg = new Fragment_Discover();
			}else if(selecTab == 2){
				fg = new Fragment_Me();
			}
			fragmentsList.put(selecTab+"", fg);//纪录
			//添加
			replaceFragment(R.id.fragmentContent, fg);
		}else{
			judgeFragment(currentFragment,totalTab,selecTab);
		}
	}
	
	
	
	/**
	 * 控制开关菜单
	 */
	public void togMenu(View view){
		slidPageMenu.toggle();
	}
    

	/*
	* Title: isOpen
	* Description: 
	* @param isOpen
	* @return 
	* @see com.yihen.jdb.ui.widget.SlidingPageMenu.ChangeMenuStateListener#isOpen(boolean) 
	*/
	@Override
	public void Tog(boolean isOpen) {
		// TODO Auto-generated method stub
		if(isOpen){
			bg_content.bringToFront();
			bg_content.setVisibility(View.VISIBLE);
		}else{
			bg_content.setVisibility(View.GONE);
			item_content.bringToFront();
		}
		
	}


	/*
	* Title: ChangeAlfa
	* Description: 
	* @param alfa 
	* @see com.yihen.jdb.ui.widget.SlidingPageMenu.ChangeMenuStateListener#ChangeAlfa(float) 
	*/
	@Override
	public void ChangeAlfa(float alfa) {
		// TODO Auto-generated method stub
		ViewHelper.setAlpha(img_header, alfa);//透明度渐变动画
	}

	
	/*
	* Title: onKeyDown
	* Description: 
	* @param keyCode
	* @param event
	* @return 
	* @see android.support.v4.app.FragmentActivity#onKeyDown(int, android.view.KeyEvent) 
	*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {//返回键实现home键功能
		// TODO Auto-generated method stub
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:	
				if(!slidPageMenu.isOpen()){
					showMenu(true, getString(R.string.logOut),getString(R.string.help),getString(R.string.exit));
				}
				//返回home桌面
//		        Intent intent = new Intent(Intent.ACTION_MAIN);  
//		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
//		        intent.addCategory(Intent.CATEGORY_HOME);  
//		        startActivity(intent);  
			break;
		case KeyEvent.KEYCODE_MENU:
					break;
		}
		return true;
	}

	/*
	* Title: onItemClick
	* Description: 
	* @param view
	* @param itemPosition 
	* @see com.yihen.jdb.ui.widget.JFActionSheetMenu.OnActionSheetItemClickListener#onItemClick(android.view.View, int) 
	*/
	public void onItemClick(View view, int itemPosition) {
		switch(itemPosition){
		case 0://注销
			startActivity(new Intent(this,LoginActivity.class));
			AppManager.getInstance().finishAllActivity();
			break;
		case 1://帮助反馈
			break;
		case 2://关闭程序
			AppManager.getInstance().AppExit(this);
			break;
		}
	}

	/*
	* Title: onCanceClick
	* Description: 
	* @param view 
	* @see com.yihen.jdb.ui.widget.JFActionSheetMenu.OnActionSheetItemClickListener#onCanceClick(android.view.View) 
	*/
	public void onCanceClick(View view) {
		// TODO Auto-generated method stub
	}

	
}
