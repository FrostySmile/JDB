package com.yihen.jdb.ui.widget;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yihen.jdb.R;

/**
 * @author xiaoyuan
 * @email yuanbin19920212@163.com
 * @date 2015/2/15 10:53
 * @description
 */
public class ToastUtil {

    /**
     * 显示在顶部
     *
     * @param context Activity上下文
     * @param text 文字
     * @param duration 显示时间
     */
    public static void makeXdpieToastTop(Context context, CharSequence text, int duration) {
        toast(context, text, duration, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 50);
    }

    /**
     * 显示在中间
     *
     * @param context Activity上下文
     * @param text 文字
     * @param duration 显示时间
     */
    public static void makeXdpieToastCenter(Context context, CharSequence text, int duration) {
        toast(context, text, duration, Gravity.CENTER, 0);
    }
    
    

    /**
     * 显示在顶部
     *
     * @param context Activity上下文
     * @param text 文字
     * @param duration 显示时间
     */
    public static void makeXdpieToastBottom(Context context, CharSequence text, int duration) {
        toast(context, text, duration, Gravity.BOTTOM,duration);
    }

    /**
     *
     * @param context Activity上下文
     * @param text 文字
     * @param duration 显示时间
     * @param gravity
     * @param yOffset
     */
    private static void toast(Context context, CharSequence text, int duration, int gravity, int yOffset) {
        toast(context, text, duration, gravity, 0, yOffset);
    }

    /**
     * 实现化Toast 并设置值
     *
     * @param context
     * @param text
     * @param duration
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    private static void toast(Context context, CharSequence text, int duration, int gravity, int xOffset, int yOffset) {
        Toast toast = new Toast(context);
        //可以自定义view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.jdb_share_toast, null);
        TextView textview = (TextView) view.findViewById(R.id.jdb_share_toast_text);
        textview.setText(text);
        toast.setView(view);
        toast.setDuration(duration);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.show();
    }
}
