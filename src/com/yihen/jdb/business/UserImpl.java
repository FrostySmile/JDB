package com.yihen.jdb.business;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yihen.jdb.bean.URLs;
import com.yihen.jdb.bean.ViewModel.Result;
import com.yihen.jdb.bean.ViewModel.UserViewModel;
import com.yihen.jdb.net.HttpUtilsExtension;
import com.yihen.jdb.tools.JsonConverter;

import java.io.IOException;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:55
 */
public class UserImpl implements IUserLab{

    private HttpUtils httpUtils;

    public UserImpl(){
        httpUtils = new HttpUtils();
    }

    @Override
    public UserViewModel login(String userName, String password) {
        try {
            String string = httpUtils.sendSync(HttpRequest.HttpMethod.GET, "http://192.168.1.121:8080/rest/customer/login.htm?loginName=" + userName + "&loginPwd=" + password).readString();
            Result<UserViewModel> result = JsonConverter.deserialize(string, new TypeToken<Result<UserViewModel>>(){}.getType());
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean loginOut(String userName) {
        return false;
    }
}
