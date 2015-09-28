package com.yihen.jdb.ui.net;

import android.test.AndroidTestCase;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yihen.jdb.bean.User;
import com.yihen.jdb.bean.ViewModel.Result;
import com.yihen.jdb.bean.ViewModel.UserViewModel;
import com.yihen.jdb.net.HttpUtilsExtension;
import com.yihen.jdb.net.param.BaseParamRequest;
import junit.framework.TestCase;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:45
 */
public class HttpUtilExtensionTests extends AndroidTestCase {

    private HttpUtilsExtension httpUtilsExtension;
    @Override
    protected void setUp() throws Exception {
        httpUtilsExtension = new HttpUtilsExtension();
        super.setUp();
    }

    /**
     * Xutils http 测试
     * @throws Exception
     */
    public void testSend() throws  Exception{
        try {
            String string = httpUtilsExtension.sendSync(HttpRequest.HttpMethod.GET,
                    "http://192.168.1.121:8080/rest/customer/login.htm?loginName=" + 1+ "&loginPwd=" + 1).readString();
            assertNotNull(string);
        }catch (Exception e){
           throw e;
        }
    }

    /**
     * httpUtilExtension测试
     * @throws Exception
     */
    public void  testBeanSend() throws Exception{
        BaseParamRequest<TestUser> baseParamRequest = new BaseParamRequest<TestUser>();
        //post方式正确，get方式有些问题
        baseParamRequest.setMethod(HttpRequest.HttpMethod.GET);
        baseParamRequest.setUrl("http://192.168.1.121:8080/rest/customer/login.htm");
        baseParamRequest.setBody(new TestUser("1","1"));
        Result<UserViewModel> result = httpUtilsExtension.sendSync(baseParamRequest);
        assertNotNull(result);
    }




    class TestUser{
        private String loginName;
        private String loginPwd;
        public TestUser(String s,String s1){
             this.loginName =s;
             this.loginPwd = s1;
        }
    }
}
