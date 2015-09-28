/**   
* @Title: AppManager.java 
* @Package com.yihen.jdb.app 
* @Description: TODO
* @author fsjohnhuang
* @date 2015-4-29 下午4:36:40 
* @version V1.0   
*/
package com.yihen.jdb.app;

import java.util.Iterator;
import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * @ClassName: AppManager
 * @Description: 应用程序管理
 * @author Frosty
 * @date 2015-4-29 下午4:36:40
 *   
 */
public class AppManager {

	private static Stack<Activity> activityStack;
	

	/**
	 * Title: can not instance
	 */
	private AppManager(){}
	
	private static class AppManagerFactory{
		private static AppManager instance = new AppManager();
	}
	
	/**
	 * @description 单一实例
	 * @param 
	 * @return AppManager
	 */
	public static AppManager getInstance()
	{
		return AppManagerFactory.instance;
	}
	

	/**
	 * @description 添加Activity到堆栈
	 * @param 
	 * @return void
	 */
	public void addActivity(Activity activity)
	{
		if(activityStack==null)
		{
			activityStack=new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	

	/**
	 * @description 获取当前Activity（堆栈中最后一个压入的）
	 * @param 
	 * @return Activity
	 */
	public Activity currentActivity()
	{
		Activity activity=activityStack.lastElement();
		return activity;
	}
	

	/**
	 * @description 结束当前Activity（堆栈中最后一个压入的）
	 * @param 
	 * @return void
	 */
	public void finishActivity()
	{
		Activity activity=activityStack.lastElement();
		finishActivity(activity.getClass());
	}
	
	/**
	 * 
	 * @description  结束指定类名的Activity
	 * @param 
	 * @return void
	 */
	public void finishActivity(Class<?> cls)
	{
		Iterator<Activity> iterator = activityStack.iterator();
		while(iterator.hasNext()){
			Activity activity = iterator.next();
			if(activity.getClass().equals(cls) )
			{
				iterator.remove();
				activity.finish();
				activity=null;
			}
		}
	}
	
	/**
	 * 
	 * @description  finish所有Activity
	 * @param 
	 * @return void
	 */
	public void finishAllActivity()
	{
		for (int i = 0, size = activityStack.size(); i < size; i++)
		{
            if (null != activityStack.get(i))
            {
            		activityStack.get(i).finish();
            }
	    }
		activityStack.clear();
	}
	
	/**
	 * @description 退出应用程序
	 * @param 
	 * @return void
	 */
	@SuppressWarnings("deprecation")
	public void AppExit(Context context) 
	{
		try {
			finishAllActivity();
			ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {	}
	}
	
	 /** 
     * 获取程序版本号 
     */  
    public long getCurrentVersionCode(Context context) {  
        int versionCode = 0;  
        try {  
            versionCode = context.getPackageManager().getPackageInfo(  
            		context.getPackageName(), 0).versionCode;  
        } catch (NameNotFoundException e) {  
        }  
        return versionCode;  
    }
	
}
