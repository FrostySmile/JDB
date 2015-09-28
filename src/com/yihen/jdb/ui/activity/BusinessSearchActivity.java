package com.yihen.jdb.ui.activity;

import android.os.Bundle;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.yihen.jdb.R;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:41
 */
@ContentView(R.layout.business_search_layout)
public class BusinessSearchActivity  extends BaseActivity{

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        ViewUtils.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
