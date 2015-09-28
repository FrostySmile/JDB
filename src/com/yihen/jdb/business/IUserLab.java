package com.yihen.jdb.business;

import com.yihen.jdb.bean.ViewModel.UserViewModel;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:46
 */
public interface IUserLab {

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    UserViewModel login(String userName,String password);

    /**
     * 用户退出登录
     * @param userName
     * @return
     */
    boolean loginOut(String userName);
}
