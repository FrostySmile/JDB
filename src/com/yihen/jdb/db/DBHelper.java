/**   
* @Title: XUtilDataBaseHelper.java 
* @Package com.yihen.jdb.db 
* @Description: TODO
* @author frosty
* @date 2015-6-2 下午2:43:50 
* @version V1.0   
*/
package com.yihen.jdb.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DaoConfig;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;
import com.yihen.jdb.app.AppConfig;
import com.yihen.jdb.app.AppConstants;
import com.yihen.jdb.business.model.ServiceDao;
import com.yihen.jdb.business.model.StoreDao;
import com.yihen.jdb.business.model.UserDao;
import com.yihen.jdb.tools.SqlParseHelper;

/**
 * @ClassName: DBHelper
 * @Description: XUtil框架实现数据库操作类
 * @author Frosty
 * @date 2015-6-2 下午2:43:50
 *   
 */
public class DBHelper {
	
	private static final String TAG = "DBhelper";
	
	/*数据库配置*/
	private static DaoConfig daoConfig;
	
	/*数据库工具类*/
	private static DbUtils db;
	
	
	/*构造私有*/
	private DBHelper(){};
	
	/*静态内部类实现单例*/
	private static class DBHelperFactory{
		private static DBHelper instance = new DBHelper();
	}
	
	/*外界获取单例*/
	public static DBHelper getInstance(){
		return DBHelperFactory.instance;
	}
  
	/**
	 * 
	 * @description 数据库初始化配置
	 * @param context
	 * @return 
	 * @return DaoConfig
	 */
	public  DaoConfig getDaoConfig(final Context context){
       if(daoConfig != null) return daoConfig;
	        daoConfig = new DbUtils.DaoConfig(context);
	        daoConfig.setDbVersion(DBConstants.DBVersion);
	        daoConfig.setDbDir(DBConstants.DBPath);
	        daoConfig.setDbName(DBConstants.DBName);
	        daoConfig.setDbUpgradeListener(new DbUpgradeListener() {
            @Override
            public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
            		//升级数据库
            		updateDB2Version(db,oldVersion,newVersion,context,false);
            }
        });
       return daoConfig;
    }
	
	/**
	 * 
	 * @description 数据库升级具体实现 
	 * @param oldVersion
	 * @param newVersion 
	 * @return void
	 */
	@SuppressWarnings("rawtypes")
	public void updateDB2Version(DbUtils dbUtils,int oldVersion, int newVersion,Context context,boolean isFirst){
		
		if (newVersion <= oldVersion) {
			return;
		}
		
		final List<Class> modelTab=new ArrayList<Class>();
		
		switch(newVersion){
			case 1://Version 1
				/*设置需要更新且不必保存数据的表模型*/
				AddModelClass(modelTab,new Class[]{UserDao.class,StoreDao.class,ServiceDao.class});
				/*创建新表*/
				CreateTableByModel(dbUtils,modelTab,context);
				/*区域信息数据表创建及数据插入*/
				ExcuteSqlFromFile(dbUtils, DBConstants.DBFileName_ADDR, newVersion, context);
			case 2://Version 2
				
			default:break;
		}
		
//		if(isInit){
//			InitBasicDataBaseAndTables(dbUtils,context);
//		}
		
	}
	
	/**
	 * 
	 * @description 添加表模型类
	 * @param classes 
	 * @return void
	 */
	@SuppressWarnings("rawtypes")
	private void AddModelClass(List<Class> modelTab,Class[] classes){
		if(classes!=null){
			modelTab.clear();
			for(int i = 0; i<classes.length; i++){
				modelTab.add(classes[i]);
			}
		}
	}
	
	/**
	 * 根据模型创建表（重新创建，有更新表结构，不保存原来数据）
	 */
	@SuppressWarnings({ "rawtypes" })
	private void CreateTableByModel(DbUtils dbUtils, List<Class> modelTab, Context context){
		try {
			for(Class c:modelTab){
				if(dbUtils.tableIsExist(c)){//如果表已经存在
					dbUtils.dropTable(c);//先删除
				}
				dbUtils.createTableIfNotExist(c);//再创建
			}
		} catch (Exception e) {
			Log.e(TAG, "升级异常:"+e.getMessage()+"");
		}finally{
			//升级成功后，记录下版本号
			SharedPreferences sp = AppConfig.getInstance(context).getSharedPrefWithName(AppConstants.DBSHAREDFILE);
			Editor editor = sp.edit();
			editor.putInt(AppConstants.DB_VERSIONCODE, DBConstants.DBVersion);
			editor.apply();
		}
	}
	
	/**
	 * 
	 * @description 执行指定sqlfile中指定id的sql语句
	 * @param dbUtils
	 * @param context 
	 * @return void
	 */
	private void ExcuteSqlFromFile(DbUtils dbUtils,String dbSqlFileName,int sqlId,Context context){
	    	List<String> list = null;
	    	try {
	    			list = SqlParseHelper.getInstance(context,dbSqlFileName).getParseList(sqlId);
			} catch (Exception e) {
				Log.e(TAG, "解析sql文件异常:"+e.getMessage()+"");
		}
		if(list == null){
		    	return;
		 }
		try {
			for (String sql : list) {
				dbUtils.execNonQuery(sql);
			}
		} catch (Exception e) {
			Log.e(TAG, "升级异常:"+e.getMessage()+"");
		}
	}
	
	 /**
     * 
     * @description 创建(打开)数据库并返回
     * @param context
     * @return 
     * @return DbUtils
     */
	public  DbUtils getDataBase(Context context){
		if(db !=null){
			return db;
		}else{
			db = DbUtils.create(getDaoConfig(context));
			return db;
		}
    }
	
	/**
	 * 
	 * @description 关闭数据库
	 * @param db 
	 * @return void
	 */
	public void closeDataBase(){
		if(db!=null){
			db.close();
		}
	}

}
