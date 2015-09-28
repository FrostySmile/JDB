/**   
* @Title: URLs.java 
* @Package com.yihen.jdb.bean 
* @Description: TODO
* @author fsjohnhuang
* @date 2015-4-30 下午3:22:52 
* @version V1.0   
*/
package com.yihen.jdb.bean;

import java.io.Serializable;

/**
 * @ClassName: URLs
 * @Description: 链接服务端的接口集合
 * @author Frosty
 * @date 2015-4-30 下午3:22:52
 *   
 */
public class URLs extends Base implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = 1L;

	public final static String HOST = "localhost";//127.0.0.1  
	public final static String HTTP = "http://";
	public final static String HTTPS = "https://";
	
	private final static String URL_SPLITTER = "/";
	private final static String URL_UNDERLINE = "_";
	
	private final static String URL_HTTP_HOST = HTTP + HOST + URL_SPLITTER;/* http://127.0.0.1 */
	private final static String URL_HTTPS_HOST  = HTTPS+HOST+URL_SPLITTER;/* https://127.0.0.1 */
	
	
	/*Server端链接地址*/
	public final static String UPDATE_VERSION = URL_HTTP_HOST+"MobileAppVersion.xml";
	
	
}
