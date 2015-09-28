package com.yihen.jdb.app;

import java.io.File;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yihen.jdb.R;
import com.yihen.jdb.db.DBConstants;
import com.yihen.jdb.db.DBHelper;
import com.yihen.jdb.tools.GetDataFromRes;
import com.yihen.jdb.tools.StringTools;
import com.yihen.jdb.ui.widget.ToastUtil;

/**
 * 
 * @author Frosty
 * @date 2015-4-27 下午3:59:19
 * @description  application实例
 *
 */

public class AppContext extends Application {

	
	/*存储应用配置文件setting.properties中的key－value*/
	public static Map<String,String> appLink = null;
	
	/*sd卡路径*/
	public static String SD_PATH = null;
	
	/*登录状态*/
	private boolean login = false;	
	
	/*登录用户的id*/
	private int loginUid = 0;
	
	@SuppressLint("HandlerLeak")
	private Handler unLoginHandler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 1){
				ToastUtil.makeXdpieToastCenter(AppContext.this, getString(R.string.msg_login_error),Toast.LENGTH_SHORT);
//				UIHelper.showLoginDialog(AppContext.this);
				
			}
		}		
	};

	
	
    @Override
    public void onCreate() {
        super.onCreate();
        //注册App异常崩溃处理器
//        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
        String storageState = Environment.getExternalStorageState();//sd卡状态
        if(storageState.equals(Environment.MEDIA_MOUNTED)){
        		SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        //加载配置文件
        LoadSetting();
        //判断是否是第一次安装，通过sharedpreference变量判断
        InitDataBase(AppConstants.DBSHAREDFILE);
        
        initImageLoader(this);
    }
    
    /**
     * 加载程序配置数据源
     */
    private void LoadSetting()
    {
    		appLink = GetDataFromRes.loadProperties(this, "settings.properties");
    }
    
    /**初始化数据库配置*/
	private void InitDataBase(String sharedPreferName){
    		SharedPreferences sp = AppConfig.getInstance(this).getSharedPrefWithName(sharedPreferName);
    		int oldVersion = AppConfig.getInstance(this).readFromSharedPrefI(AppConstants.DB_VERSIONCODE, 0, sp);//默认true
    		DbUtils db = DBHelper.getInstance().getDataBase(this);
    		if(oldVersion==0){//第一次安装，手动调用升级方法
    			DBHelper.getInstance().updateDB2Version(db,0, DBConstants.DBVersion,this,true);
    		}
    }
    		
    

    /**
     * 初始化图片加载器
     * @param context
     */
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "imageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Runtime.getRuntime().availableProcessors())
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(100 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCacheExtraOptions(context.getResources().getDisplayMetrics().widthPixels, context.getResources().getDisplayMetrics().heightPixels)
                .writeDebugLogs() // Remove for release app
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .memoryCache(new LRULimitedMemoryCache((int) (Runtime.getRuntime().maxMemory() / 8)))
                .build();
        ImageLoader.getInstance().init(config);
    }

    
    
    
    
    /**
	 * 检测网络是否可用
	 * @return
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * 获取当前网络类型
	 * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
	 */
	@SuppressLint("DefaultLocale")
	public int getNetworkType() {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}		
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if(!StringTools.isEmpty(extraInfo)){
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = AppConstants.NETTYPE_CMNET;
				} else {
					netType = AppConstants.NETTYPE_CMWAP;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = AppConstants.NETTYPE_WIFI;
		}
		return netType;
	}

    
    /**
	 * 获取App安装包信息
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try { 
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {    
			e.printStackTrace(System.err);
		} 
		if(info == null) info = new PackageInfo();
		return info;
	}


	
	
	/**
	 * 用户是否登录
	 * @return
	 */
	public boolean isLogin() {
		return login;
	}
	
	/**
	 * 获取登录用户id
	 * @return
	 */
	public int getLoginUid() {
		return this.loginUid;
	}

}
