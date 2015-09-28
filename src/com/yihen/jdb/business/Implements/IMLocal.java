/**   
* @Title: IMLocal.java 
* @Package com.yihen.jdb.business.Implements 
* @Description: TODO
* @author frosty
* @date 2015-6-11 下午2:05:12 
* @version V1.0   
*/
package com.yihen.jdb.business.Implements;

import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.yihen.jdb.business.Interface.ILocal;
import com.yihen.jdb.business.model.UserDao;
import com.yihen.jdb.db.DBHelper;

/**
 * @ClassName: IMLocal
 * @Description: TODO
 * @author Frosty
 * @date 2015-6-11 下午2:05:12
 *   
 */
public class IMLocal extends IMBase implements ILocal{

	private String TAG = "IMLocal";
	
	private  DbUtils db = null;
	
	public IMLocal(Context context){
		db = DBHelper.getInstance().getDataBase(context);
	}
	
	/*
	* Title: getLocalUserInfo
	* Description: 
	* @param telphone
	* @return 
	* @see com.yihen.jdb.business.Interface.ILocal#getLocalUserInfo(java.lang.String) 
	*/
	@Override
	public UserDao getLocalUserInfo(String telPhone) {
		UserDao user = null;
		if(db != null){
			try {
				user = db.findFirst(Selector.from(UserDao.class).where("uTel","=",telPhone));
			} catch (Exception e) {
				Log.e(TAG, e.getMessage()+"");
			}
		}
		return user;
	}

}
