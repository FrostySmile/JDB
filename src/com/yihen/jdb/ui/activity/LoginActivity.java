package com.yihen.jdb.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yihen.jdb.R;
import com.yihen.jdb.app.AppManager;

/**
 * @author xiaoyuan
 * @email yuanbin19920212@163.com
 * @date 2015/2/15 10:53
 * @description
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity{

	@ViewInject(R.id.tv_header_left) TextView tv_header_left;//返回
	@ViewInject(R.id.tv_header_middle) TextView tv_header_middle;//标题栏
	@ViewInject(R.id.login_btn) TextView btn_login;//登陆
	@ViewInject(R.id.tv_regist) TextView tv_regist;//注册
	@ViewInject(R.id.tv_resetpass) TextView tv_resetpass;//找回密码
	
	@SuppressLint("HandlerLeak")
	private  Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			switch(msg.what){
			case 1://登陆成功
				DismissDialog();
				startActivity(new Intent(LoginActivity.this,MainActivity.class));
				break;
			}
		}
		
	};
	
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        AppManager.getInstance().addActivity(this);
        ViewUtils.inject(this);
        tv_header_middle.setText(R.string.login_title);
    }
    
    @OnClick(R.id.tv_header_left)
    public void Back(View view){
    		finish();
    }
    
    //登陆
    @OnClick(R.id.login_btn)
    public void Login(View view){
    		
    		// TODO Auto-generated method stub
    		ShowDialog(this, getString(R.string.logining), true);
    		new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(2000);
						mHandler.sendEmptyMessage(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
    		
    }
    
    
    //注册
    @OnClick(R.id.tv_regist)
    public void Regist(View view){
    		startActivity(new Intent(this, RegistFirstActivity.class));
    }
    
    //找回密码
    @OnClick(R.id.tv_resetpass)
    public void ResetPass(View view){
    	
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	    	// TODO Auto-generated method stub
    		switch(keyCode){
    		case KeyEvent.KEYCODE_BACK:
			showMenu(true, getString(R.string.exit));
    			break;
    		}
	    	return super.onKeyDown(keyCode, event);
    }
    

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
		switch(itemPosition){
		case 0:AppManager.getInstance().AppExit(this);break;
		}
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
    
}
