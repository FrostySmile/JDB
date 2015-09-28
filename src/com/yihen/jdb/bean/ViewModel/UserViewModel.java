package com.yihen.jdb.bean.ViewModel;

import com.google.gson.annotations.Expose;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:47
 */
public class UserViewModel {
    @Expose
    private String userName;
    @Expose
    private String password;
    @Expose
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
