/**   
* @Title: AppConstants.java 
* @Package com.yihen.jdb.app 
* @Description: TODO
* @author frosty
* @date 2015-6-5 上午9:59:20 
* @version V1.0   
*/
package com.yihen.jdb.app;

import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;

/**
 * @ClassName: AppConstants
 * @Description: 应用全局变量配置
 * @author Frosty
 * @date 2015-6-5 上午9:59:20
 *   
 */
@SuppressLint("SimpleDateFormat")
public class AppConstants {

	/*网络类型*/
	public static final int NETTYPE_WIFI = 0x01;// wifi
	public static final int NETTYPE_CMWAP = 0x02;//2G
	public static final int NETTYPE_CMNET = 0x03;//3G
	
	//获取请求记录条数
	public static final int PAGE_SIZE = 20;//默认分页大小
	
	
	//缓存时间
	public static final int CACHE_TIME = 60*60000;//缓存失效时间
	
	/*日期时间格式化转换器*/
	//到秒
	public static final SimpleDateFormat DATE_FORMAT_ALL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	//到分
	public static final SimpleDateFormat DATE_FORMAT_MINUTE = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	//到日期
	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
	
	/*------sharedpreference------*/
	public static final String DBSHAREDFILE = "jdbShared";//文件名
	public static final String DB_VERSIONCODE="dbVersionCode";//数据库版本key
	public static final String APP_VERSION_CODE = "appVersionCode";//记录的应用版本号key,判断是否新安装或者第一此安装
	public static final String USER_PHONE = "userName";
	public static final String USER_PASS = "userPass";
	public static final String AUTO_LOGIN = "autoLogin";//是否需要自动登陆
	public static final String REMEMBER_PASS = "rememberPass";//是否记住密码
	
}
