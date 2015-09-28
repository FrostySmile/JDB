/**   
* @Title: PinyinComparator.java 
* @Package com.yihen.jdb.tools 
* @Description: TODO
* @author frosty
* @date 2015-5-25 下午5:05:58 
* @version V1.0   
*/
package com.yihen.jdb.tools;

import java.util.Comparator;

import com.yihen.jdb.business.model.StoreDao;


/**
 * @ClassName: PinyinComparator
 * @Description: 排序比较器
 * @author Frosty
 * @date 2015-5-25 下午5:05:58
 *   
 */
public class PinyinComparator implements Comparator<StoreDao> {

	public int compare(StoreDao o1, StoreDao o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
