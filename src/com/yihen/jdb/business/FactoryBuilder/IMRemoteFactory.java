/**   
* @Title: IMRemoteFactory.java 
* @Package com.yihen.jdb.business.FactoryBuilder 
* @Description: TODO
* @author frosty
* @date 2015-6-11 下午3:21:00 
* @version V1.0   
*/
package com.yihen.jdb.business.FactoryBuilder;

import android.content.Context;

import com.yihen.jdb.business.Implements.IMBase;
import com.yihen.jdb.business.Implements.IMRemote;

/**
 * @ClassName: IMRemoteFactory
 * @Description: TODO
 * @author Frosty
 * @date 2015-6-11 下午3:21:00
 *   
 */
public class IMRemoteFactory implements FactoryBuilder{


	/*
	* Title: BuilderFactory
	* Description: 远程接口实例创建工厂
	* @param context
	* @return 远程接口实现类实例
	* @see com.yihen.jdb.business.FactoryBuilder.FactoryBuilder#BuilderFactory(android.content.Context) 
	*/
	@Override
	public IMBase BuilderFactory(Context context) {
		// TODO Auto-generated method stub
		return new IMRemote(context);
	}

}
