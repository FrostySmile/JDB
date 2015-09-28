package com.yihen.jdb.bean;

import java.io.Serializable;

/**
 * 
 * @author Frosty
 * @date 2015-4-29 上午10:22:25
 * @description  用户bean
 *
 */

@SuppressWarnings("serial")
public class User extends Base implements Serializable{

	private long id;//id
	
	private String uName;//昵称
	
	private String uPass;//账户密码
	
	private String accNum;//账户
	
}
