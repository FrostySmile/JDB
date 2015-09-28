/**   
* @Title: AppConfig.java 
* @Package com.yihen.jdb.app 
* @Description: TODO
* @author fsjohnhuang
* @date 2015-4-29 下午4:53:23 
* @version V1.0   
*/
package com.yihen.jdb.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ClassName: AppConfig
 * @Description: 应用程序配置信息，Sharedpreferences文件操作
 * @author Frosty
 * @date 2015-4-29 下午4:53:23
 *   
 */

public class AppConfig {

	//上下文
	private static Context mContext;

	/**构造私有*/
	private AppConfig(){};
	
	/**静态内部类实现单例*/
	private static class AppConfigFactory{
		private static AppConfig instance = new AppConfig();
	}
	
	/**外界获取单例*/
	public static AppConfig getInstance(Context mContext){
		AppConfig.mContext = mContext;
		return AppConfigFactory.instance;
	}
 
	
	/**
	 * 获取Preference文件
	 */
	public  SharedPreferences getSharedPrefWithName(String shareName) {
		SharedPreferences shareFile = mContext.getSharedPreferences(shareName,Context.MODE_PRIVATE);   
		return shareFile;
	}
	
	/**
	 * 
	 * @description 保存数据到SharePreference文件
	 * @param key
	 * @param value 
	 * @return void
	 */
	public void saveToSharedPrefS(String key,String value, SharedPreferences shareFile){
		
		if(shareFile == null){
			return;
		}
		shareFile.edit().putString(key, value).commit();
	}
	
	/**
	 * @description 保存int
	 * @param key
	 * @param value
	 * @param shareFile 
	 * @return void
	 */
	public void saveToSharedPrefI(String key,int value,SharedPreferences shareFile){
		if(shareFile == null){
			return;
		}
		shareFile.edit().putInt(key, value).commit();
	}

	/**
	 * 
	 * @description 保存小数
	 * @param key
	 * @param value
	 * @param shareFile 
	 * @return void
	 */
	public void saveToSharedPrefF(String key,float value,SharedPreferences shareFile){
		if(shareFile == null){
			return;
		}
		shareFile.edit().putFloat(key, value).commit();
	}

	/**
	 * 
	 * @description 保存long
	 * @param key
	 * @param value
	 * @param shareFile 
	 * @return void
	 */
	public void saveToSharedPrefL(String key,Long value,SharedPreferences shareFile){
		if(shareFile == null){
			return;
		}
		shareFile.edit().putLong(key, value).commit();
	}

	/**
	 * @description 保存boolean
	 * @param key
	 * @param value
	 * @param shareFile 
	 * @return void
	 */
	public void saveToSharedPrefB(String key,Boolean value,SharedPreferences shareFile){
		if(shareFile == null){
			return;
		}
		shareFile.edit().putBoolean(key, value).commit();
	}

	
	
	

	/**
	 * 读取SharedPreferences文件的字符串数据
	 */
	public String readFromSharedPrefS(String key,String defValue,SharedPreferences shareFile)
	{
		if(null == shareFile){
			return null;
		}
		return shareFile.getString(key, defValue);
	}

	/**
	 * 读取SharedPreferences文件的整形数据
	 */
	public int readFromSharedPrefI(String key,int defValue,SharedPreferences shareFile)
	{
		if(null == shareFile){
			return -1;
		}
		return shareFile.getInt(key, defValue);
	}

	/**
	 * 读取SharedPreferences文件的小数数据
	 */
	public float readFromSharedPrefF(String key,float defValue,SharedPreferences shareFile)
	{
		if(null == shareFile){
			return -1;
		}
		return shareFile.getFloat(key, defValue);
	}

	/**
	 * 读取SharedPreferences文件的长整形数据
	 */
	public Long readFromSharedPrefL(String key,Long defValue,SharedPreferences shareFile)
	{
		if(null == shareFile){
			return -1l;
		}
		return shareFile.getLong(key, defValue);
	}

	/**
	 * 读取SharedPreferences文件的长整形数据
	 */
	public Boolean readFromSharedPrefB(String key,Boolean defValue,SharedPreferences shareFile)
	{
		if(null == shareFile){
			return defValue;
		}
		return shareFile.getBoolean(key, defValue);
	}

	
	/**
	 * 清除share指定数据
	 */
	public boolean clearValueByKey(String key,SharedPreferences shareFile){
		boolean result = false;
		if(shareFile == null){
			return result;
		}
		if(shareFile.contains(key)){
			shareFile.edit().remove(key).commit();
			result = true;
		}
		return result;
	}
	
	/**
	 * 批量删除
	 */
	public boolean clearValueByKeys(String[] keys,SharedPreferences shareFile){
		boolean result = false;
		if(shareFile == null){
			return result;
		}
		for(String key : keys){
			if(shareFile.contains(key)){
				shareFile.edit().remove(key).commit();
			}
		}
		result = true;
		return result;
	}
	
	/**
	 * 删除所有
	 */
	public boolean clearAllValue(SharedPreferences shareFile){
		boolean result = false;
		if(shareFile == null){
			return result;
		}
		shareFile.edit().clear().commit();
		result = true;
		return result;
	}
	
	/**
	 * 删除登陆信息（用户名和密码）
	 */
	public boolean clearLoginInfo(SharedPreferences shareFile){
		boolean result = false;
		if(shareFile == null){
			return result;
		}
		if(shareFile.contains(AppConstants.USER_PHONE)){
			shareFile.edit().remove(AppConstants.USER_PHONE).commit();
		}
		if(shareFile.contains(AppConstants.USER_PASS)){
			shareFile.edit().remove(AppConstants.USER_PASS).commit();
		}
		result = true;
		return result;
		
		
	}

	
}
