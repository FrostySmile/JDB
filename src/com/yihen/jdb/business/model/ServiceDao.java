/**   
* @Title: ServiceDao.java 
* @Package com.yihen.jdb.bean.dao 
* @Description: TODO
* @author frosty
* @date 2015-6-2 下午4:42:13 
* @version V1.0   
*/
package com.yihen.jdb.business.model;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * @ClassName: ServiceDao
 * @Description: 服务类别映射实体
 * @author Frosty
 * @date 2015-6-2 下午4:42:13
 *   
 */
@Table(name="tb_service")
public class ServiceDao extends BaseDao implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = 1L;

	@Column(column="serviceCode")
	private long serviceId;//服务编号
	
	@Column(column="serviceName")
	private String serviceName;//服务名称
	
	@Column(column="serviceYear")
	private int serviceYear;//服务年限
	
	@Column(column="isVisit")
	private boolean isVisit;//是否上门
	
	@Column(column="visitPay")
	private float visitPay;//上门费用
	
	@Column(column="serviceDesc")
	private String serviceDesc;//服务描述

	@Column(column="storeId")
	private long storeId;//所属店铺id
	
	/**
	 * @return the serviceId
	 */
	public long getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the serviceYear
	 */
	public int getServiceYear() {
		return serviceYear;
	}

	/**
	 * @param serviceYear the serviceYear to set
	 */
	public void setServiceYear(int serviceYear) {
		this.serviceYear = serviceYear;
	}

	/**
	 * @return the isVisit
	 */
	public boolean isVisit() {
		return isVisit;
	}

	/**
	 * @param isVisit the isVisit to set
	 */
	public void setVisit(boolean isVisit) {
		this.isVisit = isVisit;
	}

	/**
	 * @return the visitPay
	 */
	public float getVisitPay() {
		return visitPay;
	}

	/**
	 * @param visitPay the visitPay to set
	 */
	public void setVisitPay(float visitPay) {
		this.visitPay = visitPay;
	}

	/**
	 * @return the serviceDesc
	 */
	public String getServiceDesc() {
		return serviceDesc;
	}

	/**
	 * @param serviceDesc the serviceDesc to set
	 */
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	/**
	 * @return the storeId
	 */
	public long getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	
	
}
