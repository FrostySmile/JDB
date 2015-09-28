/**   
* @Title: XHttpAgent.java 
* @Package com.yihen.jdb.net 
* @Description: TODO
* @author frosty
* @date 2015-5-18 上午9:21:51 
* @version V1.0   
*/
package com.yihen.jdb.net;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @ClassName: XHttpAgent(xUtil)
 * @Description: 采用xutil框架实现的请求类
 * @author Frosty
 * @date 2015-5-18 上午9:21:51
 *   
 */
public class XHttpAgent extends BHttpAgent{

	public static final HttpMethod GET = HttpRequest.HttpMethod.GET;
	public static final HttpMethod POST = HttpRequest.HttpMethod.POST;
	private static HttpUtils xHttpUtils = null;
	
	/**
	 * 
	 * @description 获取httputils
	 * @return HttpUtils
	 */
	public static HttpUtils getXHttpUtils()
	{
		if(null == xHttpUtils)
		{
			synchronized (xHttpUtils) 
			{
				xHttpUtils = new HttpUtils(TIMEOUT_CONNECTION, UTF8);
			}
		}
		return xHttpUtils;
	}
	
	

}
