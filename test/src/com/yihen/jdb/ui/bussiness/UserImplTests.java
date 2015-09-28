package com.yihen.jdb.ui.bussiness;

import android.test.AndroidTestCase;
import com.yihen.jdb.bean.ViewModel.UserViewModel;
import com.yihen.jdb.business.IUserLab;
import com.yihen.jdb.business.UserImpl;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:25
 */
public class UserImplTests extends AndroidTestCase{

    private IUserLab userLab;

    @Override
    protected void setUp() throws Exception {
        userLab = new UserImpl();
        super.setUp();
    }

    public void testLogin() throws Exception{
        UserViewModel model = userLab.login("1", "1");
        assertNotNull(model);
    }
}
