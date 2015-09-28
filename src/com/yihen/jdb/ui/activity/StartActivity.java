package com.yihen.jdb.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import com.lidroid.xutils.view.annotation.ContentView;
import com.yihen.jdb.R;
import com.yihen.jdb.app.AppConfig;
import com.yihen.jdb.app.AppConstants;
import com.yihen.jdb.app.AppManager;

/**
 * @author xiaoyuan
 * @email yuanbin19920212@163.com
 * @date 2015/2/15 10:53
 * @description 应用程序入口
 */
@ContentView(R.layout.activity_start)
public class StartActivity extends BaseActivity{
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = AppConfig.getInstance(this).getSharedPrefWithName(AppConstants.DBSHAREDFILE);
        long oldVersionCode = AppConfig.getInstance(this).readFromSharedPrefL(AppConstants.APP_VERSION_CODE, 0L, sp);
        long newVersionCode = AppManager.getInstance().getCurrentVersionCode(this);
        if (oldVersionCode != newVersionCode ) {
        		//新安装，进入引导页
            startActivity(new Intent(this, NavigatorActivity.class));
        } else {
//        		if(TextUtils.isEmpty(AppConfig.getInstance(this).readFromSharedPrefS(AppConstants.USER_PHONE,"",sp))){//没有记录过用户名
//        			startActivity(new Intent(this, WelcomeActivity.class));
//        		}else{
//        			startActivity(new Intent(this, LoginActivity.class));
//        		}
        	startActivity(new Intent(this, NavigatorActivity.class));
        }
        finish();
    }
}
