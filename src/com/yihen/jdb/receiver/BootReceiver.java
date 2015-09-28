/**   
* @Title: BootReceiver.java 
* @Package com.yihen.jdb.receiver 
* @Description: TODO
* @author frosty
* @date 2015-7-20 下午3:58:09 
* @version V1.0   
*/
package com.yihen.jdb.receiver;

import android.content.Context;
import android.content.Intent;

/**
 * @ClassName: BootReceiver
 * @Description: 安装卸载应用广播接收
 * @author Frosty
 * @date 2015-7-20 下午3:58:09
 *   
 */
public class BootReceiver extends BaseReceiver{
	
	@Override
	public void onReceive(Context context, Intent intent) {
        //接收安装广播 
        if (intent.getAction().equals(ReceiverConstants.APP_INSTALL)) {   
            String packageName = intent.getDataString();   
            System.out.println("安装了:" +packageName + "包名的程序");     
        }   
        //接收卸载广播  
        if (intent.getAction().equals(ReceiverConstants.APP_UNINSTALL)) {   
            String packageName = intent.getDataString();   
            System.out.println("卸载了:"  + packageName + "包名的程序");
 
        }
	}

}
