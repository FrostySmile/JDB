/**   
* @Title: LinuxCommandTools.java 
* @Package com.yihen.jdb.tools 
* @Description: TODO
* @author frosty
* @date 2015-6-8 下午4:52:29 
* @version V1.0   
*/
package com.yihen.jdb.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.util.Log;

/**
 * @ClassName: LinuxCommandTools
 * @Description: 代码中执行命令
 * @author Frosty
 * @date 2015-6-8 下午4:52:29
 *   
 */
public class LinuxCommandTools {
	
	//执行命令
	public static String exec(String[] args) {  
        String result = "";  
        ProcessBuilder processBuilder = new ProcessBuilder(args);  
        Process process = null;  
        InputStream errIs = null;  
        InputStream inIs = null;  
        try {  
            ByteArrayOutputStream baos = new ByteArrayOutputStream();  
            int read = -1;  
            process = processBuilder.start();  
            errIs = process.getErrorStream();  
            while ((read = errIs.read()) != -1) {  
                baos.write(read);  
            }  
            baos.write('\n');  
            inIs = process.getInputStream();  
            while ((read = inIs.read()) != -1) {  
                baos.write(read);  
            }  
            byte[] data = baos.toByteArray();  
            result = new String(data);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (errIs != null) {  
                    errIs.close();  
                }  
                if (inIs != null) {  
                    inIs.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            if (process != null) {  
                process.destroy();  
            }  
        }  
        Log.e("44444", result);
        return result;  
    }  

}
