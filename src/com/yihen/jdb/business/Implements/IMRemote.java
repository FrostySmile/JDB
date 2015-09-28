/**   
* @Title: IMRemote.java 
* @Package com.yihen.jdb.business.Implements 
* @Description: TODO
* @author frosty
* @date 2015-6-11 下午1:57:06 
* @version V1.0   
*/
package com.yihen.jdb.business.Implements;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.yihen.jdb.business.Interface.IRemote;
import com.yihen.jdb.business.model.UserDao;
import com.yihen.jdb.db.DBHelper;

/**
 * @ClassName: IMRemote
 * @Description: TODO
 * @author Frosty
 * @date 2015-6-11 下午1:57:06
 *   
 */
public class IMRemote extends IMBase implements IRemote{

	private String TAG = "IMRemote";
	
	private  DbUtils db = null;
	
	public IMRemote(Context context){
		db = DBHelper.getInstance().getDataBase(context);
	}

	/*
	* Title: getRemoteUserInfo
	* Description: 
	* @param telPhone
	* @return 
	* @see com.yihen.jdb.business.Interface.IRemote#getRemoteUserInfo(java.lang.String) 
	*/
	@Override
	public UserDao getRemoteUserInfo(String telPhone) {
		// TODO Auto-generated method stub
		return null;
	}

}
