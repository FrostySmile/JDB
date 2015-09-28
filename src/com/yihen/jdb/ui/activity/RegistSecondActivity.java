package com.yihen.jdb.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yihen.jdb.R;
import com.yihen.jdb.app.AppManager;
import com.yihen.jdb.tools.SoftKeyboardUtils;
import com.yihen.jdb.tools.StringTools;
import com.yihen.jdb.ui.widget.ClearEditText;

/**
 * 
 * @ClassName: RegistSecondActivity
 * @Description: 注册第二个页面
 * @author Frosty
 * @date 2015-6-12 下午3:22:44
 *
 */

@ContentView(R.layout.activity_regist_second)
public class RegistSecondActivity extends BaseActivity{
	
	private static boolean flag = true;
	private static int mills = 5;
	
	@ViewInject(R.id.edt_identyCode) ClearEditText edt_identyCode;//输入验证码
	@ViewInject(R.id.edt_registpass) ClearEditText edt_registpass;//设置密码
	@ViewInject(R.id.tv_header_left) TextView tv_header_left;//返回
	@ViewInject(R.id.btn_getCode) TextView btn_getCode;//重新获取验证码
	@ViewInject(R.id.cb_changeInputType) CheckBox cb_changeInputType;//改变密码是否可见
	@ViewInject(R.id.btn_complete) TextView btn_complete;//完成
  
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 1:setTimeCalculate(Integer.valueOf(msg.arg1));//计时开始
				break;
			case 2: 
				ChangeDialogMsg("注册成功!\n即将跳转登陆页面");
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						DismissDialog();
						AppManager.getInstance().finishActivity(RegistFirstActivity.class);
						startActivity(new Intent(RegistSecondActivity.this,LoginActivity.class));//完成注册后跳转登陆
						finish();
					}
				},2000);
				break;
			}
		}
		
	};
	
	
	@Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        ViewUtils.inject(this);
        initHeaderView();
    }
    
    
    
    /**
     * 初始化顶部标签
     * @description  
     * @return void
     */
   private void  initHeaderView(){
	   tv_header_left.setText(getString(R.string.settingPass));
	   btn_getCode.setText(mills+getString(R.string.getCodeAgain));
	   startTimeCalculate();//开始计时倒数
	   edt_identyCode.addTextChangedListener(new InputTelphoneListener());
	   edt_registpass.addTextChangedListener(new InputTelphoneListener());
	   cb_changeInputType.setOnCheckedChangeListener(new ChangeInputTypeListener());
    }
   
   @OnClick(R.id.tv_header_left)
   public void ClickBack(View view){
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
			if(!TextUtils.isEmpty(edt_identyCode.getText().toString().trim())
					&& !TextUtils.isEmpty(edt_registpass.getText().toString().trim())
					&& StringTools.isPassWord(edt_registpass.getText().toString().trim())){
				btn_complete.setEnabled(true);
			}else{
				btn_complete.setEnabled(false);
			}
		}
   }
   
   
   /**
    * 
    * @ClassName: ChangeInputTypeListener
    * @Description: 改变密码是否可见
    * @author Frosty
    * @date 2015-6-15 上午9:05:23
    *
    */
   private class ChangeInputTypeListener implements OnCheckedChangeListener{

	@Override
		public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
		if(cb_changeInputType.isChecked()){
            //设置EditText的密码为可见的
			edt_registpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
         }else{
            //设置密码为隐藏的
        	 	edt_registpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
         }
		SoftKeyboardUtils.setCursorPos(edt_registpass);

	}
  }
   

   
   /**
    * 获取验证码
    * @description 
    * @param view 
    * @return void
    */
   @OnClick(R.id.btn_getCode)
   public void GetIdentyCodeAgain(View view){
	   startTimeCalculate();
   }
   
   /**
    * 
    * @description 完成注册
    * @param view 
    * @return void
    */
   @OnClick(R.id.btn_complete)
   public void Complete(View view){
	   ShowDialog(this, null, false);
	   new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					mHandler.sendEmptyMessage(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	   }).start();
   }

   

   /**
    * 
    * @description 计时器
    * @param mills 
    * @return void
    */
   private void  startTimeCalculate(){
	   flag = true;
	   btn_getCode.setEnabled(false);
	   mills = 5;
	   new Thread(new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(flag){
				if(mills <0){
					flag = false;
					return;
				}
				try {
					Message msg = Message.obtain();
					msg.what = 1;// 计时器消息标示
					msg.arg1 = mills;
					mHandler.sendMessage(msg);
					mills --;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					flag = false;
					e.printStackTrace();
				}
			}
		}
	}).start();
   }
   
   private void setTimeCalculate(int currentMills){
	   	if(currentMills>0){
	   		btn_getCode.setText(currentMills+getString(R.string.getCodeAgain));
	   	}else{
	   		btn_getCode.setEnabled(true);
	   		btn_getCode.setText(getString(R.string.getCode));
	   	}
   }
   
   
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		flag = false;
		super.onDestroy();
	}
	   
}
