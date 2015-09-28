/**   
* @Title: BHttpAgent.java 
* @Package com.yihen.jdb.net 
* @Description: TODO
* @author frosty
* @date 2015-5-18 下午2:48:34 
* @version V1.0   
*/
package com.yihen.jdb.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @ClassName: BHttpAgent (Base)
 * @Description: 网络请求的基本类，DHttpAgent(自带)和XHttpAgent(三方)扩展此类
 * @author Frosty
 * @date 2015-5-18 下午2:48:34
 *   
 */
public class BHttpAgent {

	protected static final String UTF8 = "UTF-8";
	protected final static int TIMEOUT_CONNECTION = 20000;//链接超时
	protected final static int TIMEOUT_SOCKET = 20000;//读数据超时时间
	protected final static int RETRY_TIME = 3;//重试次数

	
	/**
	 * 
	 * @description 组装完整的url地址
	 * @param p_url
	 * @param params
	 * @return 
	 * @return String
	 */
	public static String MakeURL(String p_url, Map<String, Object> params) {
		StringBuilder url = new StringBuilder(p_url);
		try {
			if(url.indexOf("?")<0)
				url.append('?');
			for(String name : params.keySet()){
				url.append('&');
				url.append(name);
				url.append('=');
				url.append(String.valueOf(params.get(name)));
				//URLEncoder处理
				url.append(URLEncoder.encode(String.valueOf(params.get(name)), UTF8));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url.toString().replace("?&", "?");
	}

	
}
