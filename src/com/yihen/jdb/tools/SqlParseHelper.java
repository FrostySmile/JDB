/**   
* @Title: SqlParseHelper.java 
* @Package com.yihen.jdb.tools 
* @Description: TODO
* @author frosty
* @date 2015-5-17 下午9:13:41 
* @version V1.0   
*/
package com.yihen.jdb.tools;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

/**
 * @ClassName: SqlParseHelper
 * @Description: SQL语句解析器
 * @author Frosty
 * @date 2015-5-17 下午9:13:41
 *   
 */

public class SqlParseHelper {
	
	private static String TAG = "SqlParseHelper";
	
	private static Context mCtx;
	
	/** 当前版本号 **/
	private int curId = -1;

	/** 节点 **/
	private static final String SQL = "sql";
	
	private static final String CONTENT = "content";
	
	private static SqlParseHelper instance = null;
	
	/** 数据库sql文件名称 **/
	private static  String FILE = null;
	
	/** 编码方式 **/
	private static final String ENCODING = "UTF-8";
	
	
	/*私有构造函数*/
	private SqlParseHelper() {
	}
	
	/**静态内部类实现单例*/
	private static class SqlParseHelperFactory{
		private static SqlParseHelper instance = new SqlParseHelper(); 
	}
	
	
	/**外界获取单例*/
	public static SqlParseHelper getInstance(Context mCtx,String sqlFileName){
		SqlParseHelper.mCtx = mCtx;
		FILE = sqlFileName;
		return SqlParseHelperFactory.instance;
	}
	
	
	/**
	 * 获取对应的sql语句列表
	 * 
	 * @param id
	 *            版本标识；
	 * @return
	 */
	public List<String> getParseList(int id) {
		if(TextUtils.isEmpty(FILE)) return null;
		List<String> list = null;
		XmlPullParser pullParser = Xml.newPullParser();
		try {
			// 为解析器设置要解析的XML数据
			pullParser.setInput(mCtx.getAssets().open(FILE), ENCODING);
			int event = pullParser.getEventType();
			// 循环遍历每个元素只到文档末尾
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					list = new ArrayList<String>();
					break;
				case XmlPullParser.START_TAG:// 标签开始
					if (pullParser.getName().equals(SQL) && pullParser.getAttributeCount() > 0) {
						curId = Integer.valueOf(pullParser.getAttributeValue(0));
					}
					if (curId == id && pullParser.getName().equals(CONTENT)) {
						String content = pullParser.nextText();
						list.add(content);
					}
					break;
				}
				event = pullParser.next();
			}
		} catch (Exception e) {
			Log.e(TAG, "pareser:"+e.getMessage()+"");
		}
		return list;
	}
	
}