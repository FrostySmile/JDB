/**   
* @Title: IMRemote.java 
* @Package com.yihen.jdb.business.Implements 
* @Description: TODO
* @author frosty
* @date 2015-6-11 下午1:50:20 
* @version V1.0   
*/
package com.yihen.jdb.business.Interface;

import com.yihen.jdb.business.model.UserDao;

/**
 * @ClassName: IRemote
 * @Description: 远程访问接口定义
 * @author Frosty
 * @date 2015-6-11 下午1:50:20
 *   
 */
public interface IRemote extends IBase{

	public UserDao getRemoteUserInfo(String telPhone);
	
}
