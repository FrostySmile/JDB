/**   
* @Title: Attention.java 
* @Package com.yihen.jdb.bean.dto 
* @Description: TODO
* @author frosty
* @date 2015-5-25 上午10:46:10 
* @version V1.0   
*/
package com.yihen.jdb.bean.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: StoreDto
 * @Description: 商店传输对象
 * @author Frosty
 * @date 2015-5-25 上午10:46:10
 *   
 */
public class StoreDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private  long sId;//店铺id
	
	private String sName;// 店铺名
	
	private long sAddressCode;//地理位置编码
	
	private String sAddress;//店铺地理位置名称
	
	private Double sLongitude;//店铺经度
	
	private Double sLatitude;//店铺纬度
	
	private String sIconUrl;//店铺头像地址
	
	private String sDesc;//店铺描述
	
	private String sRank;//店铺等级
	
	private String sAddedTime;//店铺添加时间
	
	private long sUpdateCode;//最近更新编号（用户可对比判断是否重新从网络获取更新数据）

	private int sFoundYear;//店铺成立多少年
	
	private int sFans;//店铺的粉丝数
	
	private List<ServiceDto> sService;//店铺主要服务项目

	private long sManagerId;//店铺所有者id，关联到人员信息
	
	
	
	
	
	
}
