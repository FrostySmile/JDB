/**   
* @Title: BaseDao.java 
* @Package com.yihen.jdb.bean.dao 
* @Description: TODO
* @author frosty
* @date 2015-6-2 下午3:38:34 
* @version V1.0   
*/
package com.yihen.jdb.business.model;

import java.io.Serializable;


/**
 * @ClassName: BaseDao
 * @Description: 映射实体基类,每张表的主键
 * @author Frosty
 * @date 2015-6-2 下午3:38:34
 *   
 */
public class BaseDao implements Serializable{

	private static final long serialVersionUID = 1L;
	//@Id // 如果主键没有命名名为id或_id的时，需要为主键添加此注解
    //@NoAutoIncrement // int,long类型的id默认自增，不想使用自增时添加此注解
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
