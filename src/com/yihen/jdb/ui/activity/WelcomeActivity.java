package com.yihen.jdb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yihen.jdb.R;
import com.yihen.jdb.app.AppManager;



/**
 * 
 * @ClassName: WelcomeActivity
 * @Description: 欢迎页面
 * @author Frosty
 * @date 2015-6-11 下午5:48:56
 *
 */
@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity{
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        ViewUtils.inject(this);
    }
    
    @OnClick(R.id.regist_btn)
    public void registNow(View view){
    		startActivity(new Intent(this, RegistFirstActivity.class));
    }
    
    @OnClick(R.id.login_btn)
    public void loginNow(View view){
    		startActivity(new Intent(this, LoginActivity.class));
    		finish();
    }

    

}
