/**   
* @Title: DBConstants.java 
* @Package com.yihen.jdb.db 
* @Description: TODO
* @author frosty
* @date 2015-5-17 下午8:58:11 
* @version V1.0   
*/
package com.yihen.jdb.db;

import com.yihen.jdb.app.AppContext;

/**
 * @ClassName: DBConstants
 * @Description: 数据库相关常量
 * @author Frosty
 * @date 2015-5-17 下午8:58:11
 *   
 */
public class DBConstants {

	//----------------------全局配置----------------------//
	public static final String DBPath =AppContext.SD_PATH+AppContext.appLink.get("app.rootDir")+AppContext.appLink.get("app.dbDir");
	
	public static final String DBName = AppContext.appLink.get("db.dbName");//数据库名称
	
	public static final String DBFileName_MAIN = AppContext.appLink.get("db.sqlFileMain");//主sql语句文件
	
	public static final String DBFileName_ADDR = AppContext.appLink.get("db.sqlFileAddr");//区域sql语句文件
	
	public static final int DBVersion = 4;//数据库版本
	
	
}
