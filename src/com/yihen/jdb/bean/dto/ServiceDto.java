/**   
* @Title: ServiceType.java 
* @Package com.yihen.jdb.bean.dto 
* @Description: TODO
* @author frosty
* @date 2015-6-1 下午3:28:19 
* @version V1.0   
*/
package com.yihen.jdb.bean.dto;

import java.io.Serializable;

/**
 * @ClassName: ServiceType
 * @Description: 服务条目 修电视、开锁、修热水器等
 * @author Frosty
 * @date 2015-6-1 下午3:28:19
 *   
 */
public class ServiceDto implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = 1L;

	private long serviceCode;//服务编号
	
	private String serviceName;//服务名称
	
	private String serviceYear;//服务年限
	
	private boolean isVisit;//是否上门
	
	private float visitPay;//上门费用
	
	private String serviceDesc;//服务描述

	/**
	 * @return the serviceCode
	 */
	public long getServiceCode() {
		return serviceCode;
	}

	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(long serviceCode) {
		this.serviceCode = serviceCode;
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
	public String getServiceYear() {
		return serviceYear;
	}

	/**
	 * @param serviceYear the serviceYear to set
	 */
	public void setServiceYear(String serviceYear) {
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
	
	
	
	
}
