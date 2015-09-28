package com.yihen.jdb.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:20
 */
public class SharedPreferenceTools {

    private static String mSavePath = "jdb";

    private static String START_STATE_INFO = "start_state_info";

    private static SharedPreferences.Editor getEditor(Context context){
        return context.getSharedPreferences(mSavePath,context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(mSavePath, context.MODE_PRIVATE);
    }

    /**
     * 设置获取程序状态
     * @param context
     * @param flag
     */
    public static void setStartStateInfo(Context context,boolean flag){
          getEditor(context).putBoolean(START_STATE_INFO,flag).commit();
    }

    public static boolean getStartStateInfo(Context context){
        return getSharedPreferences(context).getBoolean(START_STATE_INFO,false);
    }
}
