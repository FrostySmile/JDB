/**   
* @Title: IMLocal.java 
* @Package com.yihen.jdb.business.Implements 
* @Description: TODO
* @author frosty
* @date 2015-6-11 下午1:51:14 
* @version V1.0   
*/
package com.yihen.jdb.business.Interface;

import com.yihen.jdb.business.model.UserDao;

/**
 * @ClassName: ILocal
 * @Description: 本地接口定义
 * @author Frosty
 * @date 2015-6-11 下午1:51:14
 *   
 */
public interface ILocal extends IBase{
	
	/*获取本地用户信息，即登陆者信息*/
	public  UserDao getLocalUserInfo(String telPhone);

}
