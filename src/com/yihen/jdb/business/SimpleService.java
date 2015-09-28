package com.yihen.jdb.business;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by G_code on 2015/2/15.
 */
public class SimpleService extends IntentService {

    public SimpleService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
