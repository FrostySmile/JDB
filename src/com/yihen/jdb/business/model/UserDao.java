/**   
* @Title: UserDao.java 
* @Package com.yihen.jdb.bean.dao 
* @Description: TODO
* @author frosty
* @date 2015-6-8 上午8:49:30 
* @version V1.0   
*/
package com.yihen.jdb.business.model;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

/**
 * @ClassName: UserDao
 * @Description: 登录用户实体
 * @author Frosty
 * @date 2015-6-8 上午8:49:30
 *   
 */
@Table(name = "tb_user")
public class UserDao extends BaseDao implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Unique
	@Column(column = "uId")
	private long uId;//用户id
	
	@Column(column = "uPetName")
	private String uPetName;//用户昵称
	
	@Column(column = "uRemoteHeaderUrl")
	private String uRemoteHeaderUrl;//远程头像路径
	
	@Column(column = "uLocalHeaderUrl")
	private String uLocalHeaderUrl;//本地头像路径
	
	@Column(column = "uRealName")
	private String uRealName;//真实姓名
	
	@Column(column = "uAddress")
	private String uAddress;//所在地市区域
	
	@Column(column = "uAddressCode")
	private String uAddressCode;//所在地市区域编码

	@Column(column = "uTel")
	private String uTel;//用户电话
	
	@Column(column = "uEmail")
	private String uEmail;//用户邮箱
	
	@Column(column = "uSex")
	private int uSex;//性别
	
	@Column(column = "uBirthday")
	private String uBirthday;//出生日期
	
	@Column(column = "uType")
	private int uType;//用户类型1:找服务 2:提供服务
	
	@Column(column="uAttentionStoreIds")
	private String uAttentionStoreIds;//用户关注的店铺id集合拼接
	
	@Column(column="uUpdateCode")
	private long uUpdateCode;//最近更新编号,以系统毫秒时间参考（用户可对比判断是否重新从网络获取更新数据）

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
	 * @return the uRemoteHeaderUrl
	 */
	public String getuRemoteHeaderUrl() {
		return uRemoteHeaderUrl;
	}

	/**
	 * @param uRemoteHeaderUrl the uRemoteHeaderUrl to set
	 */
	public void setuRemoteHeaderUrl(String uRemoteHeaderUrl) {
		this.uRemoteHeaderUrl = uRemoteHeaderUrl;
	}

	/**
	 * @return the uLocalHeaderUrl
	 */
	public String getuLocalHeaderUrl() {
		return uLocalHeaderUrl;
	}

	/**
	 * @param uLocalHeaderUrl the uLocalHeaderUrl to set
	 */
	public void setuLocalHeaderUrl(String uLocalHeaderUrl) {
		this.uLocalHeaderUrl = uLocalHeaderUrl;
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
	 * @return the uAddressCode
	 */
	public String getuAddressCode() {
		return uAddressCode;
	}

	/**
	 * @param uAddressCode the uAddressCode to set
	 */
	public void setuAddressCode(String uAddressCode) {
		this.uAddressCode = uAddressCode;
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
	 * @return the uAttentionStoreIds
	 */
	public String getuAttentionStoreIds() {
		return uAttentionStoreIds;
	}

	/**
	 * @param uAttentionStoreIds the uAttentionStoreIds to set
	 */
	public void setuAttentionStoreIds(String uAttentionStoreIds) {
		this.uAttentionStoreIds = uAttentionStoreIds;
	}

	/**
	 * @return the uUpdateCode
	 */
	public long getuUpdateCode() {
		return uUpdateCode;
	}

	/**
	 * @param uUpdateCode the uUpdateCode to set
	 */
	public void setuUpdateCode(long uUpdateCode) {
		this.uUpdateCode = uUpdateCode;
	}

}
