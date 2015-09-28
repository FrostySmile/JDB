/**   
* @Title: User.java 
* @Package com.yihen.jdb.bean.dto 
* @Description: TODO
* @author frosty
* @date 2015-5-26 下午2:27:22 
* @version V1.0   
*/
package com.yihen.jdb.bean.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserDto
 * @Description: 个人用户／企业用户 传输对象
 * @author Frosty
 * @date 2015-5-26 下午2:27:22
 *   
 */
public class UserDto implements Serializable{

	
	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = 1L;

	/**通用字段*/
	private long uId;//用户id
	
	private String uPetName;//用户昵称
	
	private String uRealName;//真实姓名
	
	private String uAddress;//所在地市区域
	
	private String uTel;//用户电话
	
	private String uEmail;//用户邮箱
	
	private int uSex;//性别
	
	private String uBirthday;//出生日期
	
	private List<Long> uAttentionUserId;//关注的用户id
	
	private int uType;//用户类型1:找服务 2:提供服务(个人) 3:提供服务(企业)
	
	/**根据用户类型，以下字段可有可无*/
	
	//提供服务者 具有该属性值
	private long uFans;//粉丝个数
	
	//提供服务者 具有该属性值
	private List<StoreDto> uStores;//用户拥有的商店对象
	
	//店铺员工 具有该属性值
	private long uWorkStoreId;//就职的店铺id(用于扩展)

	/**
	 * @return the uId
	 */
	public long getuId() {
		return uId;
	}

	/**
	 * @param uId the uId to set
	 */
	public void setuId(long uId) {
		this.uId = uId;
	}

	/**
	 * @return the uPetName
	 */
	public String getuPetName() {
		return uPetName;
	}

	/**
	 * @param uPetName the uPetName to set
	 */
	public void setuPetName(String uPetName) {
		this.uPetName = uPetName;
	}

	/**
	 * @return the uRealName
	 */
	public String getuRealName() {
		return uRealName;
	}

	/**
	 * @param uRealName the uRealName to set
	 */
	public void setuRealName(String uRealName) {
		this.uRealName = uRealName;
	}

	/**
	 * @return the uAddress
	 */
	public String getuAddress() {
		return uAddress;
	}

	/**
	 * @param uAddress the uAddress to set
	 */
	public void setuAddress(String uAddress) {
		this.uAddress = uAddress;
	}

	/**
	 * @return the uTel
	 */
	public String getuTel() {
		return uTel;
	}

	/**
	 * @param uTel the uTel to set
	 */
	public void setuTel(String uTel) {
		this.uTel = uTel;
	}

	/**
	 * @return the uEmail
	 */
	public String getuEmail() {
		return uEmail;
	}

	/**
	 * @param uEmail the uEmail to set
	 */
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	/**
	 * @return the uSex
	 */
	public int getuSex() {
		return uSex;
	}

	/**
	 * @param uSex the uSex to set
	 */
	public void setuSex(int uSex) {
		this.uSex = uSex;
	}

	/**
	 * @return the uBirthday
	 */
	public String getuBirthday() {
		return uBirthday;
	}

	/**
	 * @param uBirthday the uBirthday to set
	 */
	public void setuBirthday(String uBirthday) {
		this.uBirthday = uBirthday;
	}

	/**
	 * @return the uAttentionUserId
	 */
	public List<Long> getuAttentionUserId() {
		return uAttentionUserId;
	}

	/**
	 * @param uAttentionUserId the uAttentionUserId to set
	 */
	public void setuAttentionUserId(List<Long> uAttentionUserId) {
		this.uAttentionUserId = uAttentionUserId;
	}

	/**
	 * @return the uType
	 */
	public int getuType() {
		return uType;
	}

	/**
	 * @param uType the uType to set
	 */
	public void setuType(int uType) {
		this.uType = uType;
	}
	
	/**
	 * @return the uFans
	 */
	public long getuFans() {
		return uFans;
	}

	/**
	 * @param uFans the uFans to set
	 */
	public void setuFans(long uFans) {
		this.uFans = uFans;
	}

	/**
	 * @return the uStores
	 */
	public List<StoreDto> getuStores() {
		return uStores;
	}

	/**
	 * @param uStores the uStores to set
	 */
	public void setuStores(List<StoreDto> uStores) {
		this.uStores = uStores;
	}

	/**
	 * @return the uWorkStoreId
	 */
	public long getuWorkStoreId() {
		return uWorkStoreId;
	}

	/**
	 * @param uWorkStoreId the uWorkStoreId to set
	 */
	public void setuWorkStoreId(long uWorkStoreId) {
		this.uWorkStoreId = uWorkStoreId;
	}
	
	
	
	
}
