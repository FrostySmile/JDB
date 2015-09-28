/**   
* @Title: AttentionDao.java 
* @Package com.yihen.jdb.bean.dao 
* @Description: TODO
* @author frosty
* @date 2015-6-1 下午3:48:13 
* @version V1.0   
*/
package com.yihen.jdb.business.model;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Foreign;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

/**
 * @ClassName: StoreDao
 * @Description: 店铺信息表映射实体
 * @author Frosty
 * @date 2015-6-1 下午3:48:13
 * 
 */

@Table(name="tb_store")
public class StoreDao extends BaseDao implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Unique	//唯一性，商店的外键关联字段
	@Column(column="sId")
	private long sId;//店铺id
	
	@Column(column="sName")
	private String sName;// 店铺名
	
	@Column(column="sAddressCode")
	private int sAddressCode;//地理位置编码
	
	@Column(column="sAddress")
	private String sAddress;//店铺地理位置名称
	
	@Column(column="sLongitude")
	private Double sLongitude;//店铺经度
	
	@Column(column="sLatitude")
	private Double sLatitude;//店铺纬度

	@Column(column="sHeaderUrl")
	private String sHeaderUrl;//店铺头像远程地址
	
	@Column(column="sLocalHeaderUrl")
	private String sLocalHeaderUrl;//店铺头像本地路径
	
	@Column(column="sDesc")
	private String sDesc;//店铺描述
	
	@Column(column="sRank")
	private int sRank;//店铺等级
	
	@Column(column="sAddedTime")
	private String sAddedTime;//店铺添加时间
	
	@Column(column="sUpdateCode")
	private long sUpdateCode;//最近更新编号,以系统毫秒时间参考（用户可对比判断是否重新从网络获取更新数据）
	
	@Column(column="sFoundYear")
	private int sFoundYear;//店铺成立多少年
	
	@Column(column="sFans")
	private int sFans;//粉丝人数
	
	@Column(column="sDescriptionImgs")
	private String sDescriptionImgs;//店铺照片信息链接url,用特殊符号分割开，最多四张
	
	@Column(column="storeKeeperId")
	private long storeKeeperId;//店主id
	
	@Column(column="storeKeeperName")
	private String storeKeeperName;//店主名称
	
	@Column(column="storeKeeperSex")
	private int sotreKeeperSex;//店主性别
	
	@Column(column="storeTelphone")
	private String storeTelphone;//店铺联系方式
	
	//显示数据拼音的首字母用于分组排序
	private String sortLetters;

	/**
	 * @return the sId
	 */
	public long getsId() {
		return sId;
	}

	/**
	 * @param sId the sId to set
	 */
	public void setsId(long sId) {
		this.sId = sId;
	}

	/**
	 * @return the sName
	 */
	public String getsName() {
		return sName;
	}

	/**
	 * @param sName the sName to set
	 */
	public void setsName(String sName) {
		this.sName = sName;
	}

	/**
	 * @return the sAddressCode
	 */
	public int getsAddressCode() {
		return sAddressCode;
	}

	/**
	 * @param sAddressCode the sAddressCode to set
	 */
	public void setsAddressCode(int sAddressCode) {
		this.sAddressCode = sAddressCode;
	}

	/**
	 * @return the sAddress
	 */
	public String getsAddress() {
		return sAddress;
	}

	/**
	 * @param sAddress the sAddress to set
	 */
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}

	/**
	 * @return the sLongitude
	 */
	public Double getsLongitude() {
		return sLongitude;
	}

	/**
	 * @param sLongitude the sLongitude to set
	 */
	public void setsLongitude(Double sLongitude) {
		this.sLongitude = sLongitude;
	}

	/**
	 * @return the sLatitude
	 */
	public Double getsLatitude() {
		return sLatitude;
	}

	/**
	 * @param sLatitude the sLatitude to set
	 */
	public void setsLatitude(Double sLatitude) {
		this.sLatitude = sLatitude;
	}

	/**
	 * @return the sHeaderUrl
	 */
	public String getsHeaderUrl() {
		return sHeaderUrl;
	}

	/**
	 * @param sHeaderUrl the sHeaderUrl to set
	 */
	public void setsHeaderUrl(String sHeaderUrl) {
		this.sHeaderUrl = sHeaderUrl;
	}

	/**
	 * @return the sLocalHeaderUrl
	 */
	public String getsLocalHeaderUrl() {
		return sLocalHeaderUrl;
	}

	/**
	 * @param sLocalHeaderUrl the sLocalHeaderUrl to set
	 */
	public void setsLocalHeaderUrl(String sLocalHeaderUrl) {
		this.sLocalHeaderUrl = sLocalHeaderUrl;
	}

	/**
	 * @return the sDesc
	 */
	public String getsDesc() {
		return sDesc;
	}

	/**
	 * @param sDesc the sDesc to set
	 */
	public void setsDesc(String sDesc) {
		this.sDesc = sDesc;
	}

	/**
	 * @return the sRank
	 */
	public int getsRank() {
		return sRank;
	}

	/**
	 * @param sRank the sRank to set
	 */
	public void setsRank(int sRank) {
		this.sRank = sRank;
	}

	/**
	 * @return the sAddedTime
	 */
	public String getsAddedTime() {
		return sAddedTime;
	}

	/**
	 * @param sAddedTime the sAddedTime to set
	 */
	public void setsAddedTime(String sAddedTime) {
		this.sAddedTime = sAddedTime;
	}

	/**
	 * @return the sUpdateCode
	 */
	public long getsUpdateCode() {
		return sUpdateCode;
	}

	/**
	 * @param sUpdateCode the sUpdateCode to set
	 */
	public void setsUpdateCode(long sUpdateCode) {
		this.sUpdateCode = sUpdateCode;
	}

	/**
	 * @return the sFoundYear
	 */
	public int getsFoundYear() {
		return sFoundYear;
	}

	/**
	 * @param sFoundYear the sFoundYear to set
	 */
	public void setsFoundYear(int sFoundYear) {
		this.sFoundYear = sFoundYear;
	}

	/**
	 * @return the sFans
	 */
	public int getsFans() {
		return sFans;
	}

	/**
	 * @param sFans the sFans to set
	 */
	public void setsFans(int sFans) {
		this.sFans = sFans;
	}

	/**
	 * @return the sDescriptionImgs
	 */
	public String getsDescriptionImgs() {
		return sDescriptionImgs;
	}

	/**
	 * @param sDescriptionImgs the sDescriptionImgs to set
	 */
	public void setsDescriptionImgs(String sDescriptionImgs) {
		this.sDescriptionImgs = sDescriptionImgs;
	}

	/**
	 * @return the storeKeeperId
	 */
	public long getStoreKeeperId() {
		return storeKeeperId;
	}

	/**
	 * @param storeKeeperId the storeKeeperId to set
	 */
	public void setStoreKeeperId(long storeKeeperId) {
		this.storeKeeperId = storeKeeperId;
	}

	/**
	 * @return the storeKeeperName
	 */
	public String getStoreKeeperName() {
		return storeKeeperName;
	}

	/**
	 * @param storeKeeperName the storeKeeperName to set
	 */
	public void setStoreKeeperName(String storeKeeperName) {
		this.storeKeeperName = storeKeeperName;
	}

	/**
	 * @return the sotreKeeperSex
	 */
	public int getSotreKeeperSex() {
		return sotreKeeperSex;
	}

	/**
	 * @param sotreKeeperSex the sotreKeeperSex to set
	 */
	public void setSotreKeeperSex(int sotreKeeperSex) {
		this.sotreKeeperSex = sotreKeeperSex;
	}

	/**
	 * @return the storeTelphone
	 */
	public String getStoreTelphone() {
		return storeTelphone;
	}

	/**
	 * @param storeTelphone the storeTelphone to set
	 */
	public void setStoreTelphone(String storeTelphone) {
		this.storeTelphone = storeTelphone;
	}

	/**
	 * @return the sortLetters
	 */
	public String getSortLetters() {
		return sortLetters;
	}

	/**
	 * @param sortLetters the sortLetters to set
	 */
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}



}
