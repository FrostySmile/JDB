/**   
* @Title: IMLocalFactory.java 
* @Package com.yihen.jdb.business.FactoryBuilder 
* @Description: TODO
* @author frosty
* @date 2015-6-11 下午3:30:23 
* @version V1.0   
*/
package com.yihen.jdb.business.FactoryBuilder;

import android.content.Context;

import com.yihen.jdb.business.Implements.IMBase;
import com.yihen.jdb.business.Implements.IMLocal;

/**
 * @ClassName: IMLocalFactory
 * @Description: TODO
 * @author Frosty
 * @date 2015-6-11 下午3:30:23
 *   
 */
public class IMLocalFactory implements FactoryBuilder{

	/*
	* Title: BuilderFactory
	* Description: 本地接口创建工厂
	* @param context
	* @return 本地接口实现类实例
	* @see com.yihen.jdb.business.FactoryBuilder.FactoryBuilder#BuilderFactory(android.content.Context) 
	*/
	@Override
	public IMBase BuilderFactory(Context context) {
		// TODO Auto-generated method stub
		return new IMLocal(context);
	}

}
