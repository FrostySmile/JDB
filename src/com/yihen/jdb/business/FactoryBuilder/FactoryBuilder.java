/**   
* @Title: FactoryBuilder.java 
* @Package com.yihen.jdb.business.FactoryBuilder 
* @Description: TODO
* @author frosty
* @date 2015-6-11 下午3:17:39 
* @version V1.0   
*/
package com.yihen.jdb.business.FactoryBuilder;

import android.content.Context;

import com.yihen.jdb.business.Implements.IMBase;

/**
 * @ClassName: FactoryBuilder
 * @Description: 抽象工厂
 * @author Frosty
 * @date 2015-6-11 下午3:17:39
 *   
 */
public interface FactoryBuilder {

	abstract IMBase BuilderFactory(Context context);
	
}
