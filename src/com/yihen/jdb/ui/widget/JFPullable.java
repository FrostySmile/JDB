/**   
* @Title: JFPullable.java 
* @Package com.yihen.jdb.ui.widget 
* @Description: TODO
* @author frosty
* @date 2015-5-5 下午2:42:00 
* @version V1.0   
*/
package com.yihen.jdb.ui.widget;

/**
 * @ClassName: JFPullable
 * @Description: 刷新接口
 * @author Frosty
 * @date 2015-5-5 下午2:42:00
 *   
 */
public interface JFPullable {

	/**
	 * 判断是否可以下拉，如果不需要下拉功能可以直接return false
	 * 
	 * @return true如果可以下拉否则返回false
	 */
	boolean canPullDown();

	/**
	 * 判断是否可以上拉，如果不需要上拉功能可以直接return false
	 * 
	 * @return true如果可以上拉否则返回false
	 */
	boolean canPullUp();

	
}
