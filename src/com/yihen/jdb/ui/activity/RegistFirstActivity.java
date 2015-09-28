package com.yihen.jdb.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yihen.jdb.R;
import com.yihen.jdb.app.AppManager;
import com.yihen.jdb.tools.StringTools;
import com.yihen.jdb.ui.widget.ClearEditText;
import com.yihen.jdb.ui.widget.LoadingDialog;
import com.yihen.jdb.ui.widget.ToastUtil;

/**
 * 
 * @ClassName: RegistActivity
 * @Description: TODO
 * @author Frosty
 * @date 2015-6-12 下午3:22:44
 *
 */

@ContentView(R.layout.activity_regist_first)
public class RegistFirstActivity extends BaseActivity{
	LoadingDialog loading = null;
	@ViewInject(R.id.tv_header_left) TextView tv_header_left;//返回
	@ViewInject(R.id.tv_header_middle) TextView tv_header_middle;//标题
	
	@ViewInject(R.id.edt_phone) ClearEditText edt_phone;
	@ViewInject(R.id.btn_next) TextView btn_next;
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 1://手机验证成功
				startActivity(new Intent(RegistFirstActivity.this,RegistSecondActivity.class));
				DismissDialog();
				break;
			}
		}
		
	};
	
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        AppManager.getInstance().addActivity(this);
        ViewUtils.inject(this);
        initHeaderView();
    }
    
    
    
    /**
     * 初始化顶部标签
     * @description  
     * @return void
     */
   private void  initHeaderView(){
	   tv_header_middle.setText(R.string.regist);
	   edt_phone.addTextChangedListener(new InputTelphoneListener());
    }
   
   @OnClick(R.id.tv_header_left)
   public void Back(View view){
	   finish();
   }
   
   
   /**
    * 
    * @ClassName: InputTelphoneListener
    * @Description: 输入电话号码监听类
    * @author Frosty
    * @date 2015-6-12 下午4:26:37
    *
    */
   private class InputTelphoneListener implements TextWatcher{

	@Override
	public void afterTextChanged(Editable arg0) {
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
	}

	@Override
	public void onTextChanged(CharSequence str, int arg1, int arg2, int arg3) {
		
			if(!TextUtils.isEmpty(str) && str.toString().length() ==11){
				btn_next.setEnabled(true);
			}else{
				btn_next.setEnabled(false);
			}
		}
   }
   
   
   /**
    * 第一次获取验证码
    * @description 
    * @param view 
    * @return void
    */
   @OnClick(R.id.btn_next)
   public void NextStep(View view){
	   if(StringTools.isPhone(edt_phone.getText().toString().trim())){
		   ShowDialog(this, null,false);
		   new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					mHandler.sendEmptyMessage(1);
				}
			}
		}).start();
	   }else{
		   //弹出提示
		   ToastUtil.makeXdpieToastCenter(this, getString(R.string.isNotPhone), Toast.LENGTH_SHORT);
		   edt_phone.setText("");
	   }
   }
   
   /*
* Title: onDestroy
* Description:  
* @see android.support.v4.app.FragmentActivity#onDestroy() 
*/
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	DismissDialog();
	super.onDestroy();
	
}
   
}
