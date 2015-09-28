/**   
* @Title: FileTools.java 
* @Package com.yihen.jdb.tools 
* @Description: TODO
* @author frosty
* @date 2015-5-17 下午10:08:43 
* @version V1.0   
*/
package com.yihen.jdb.tools;

import java.io.File;

import android.net.Uri;

/**
 * @ClassName: FileTools
 * @Description: 文件操作
 * @author Frosty
 * @date 2015-5-17 下午10:08:43
 *   
 */

public class FileTools {
    
    /**
     * 得到指定文件路径的父目录
     * 
     * @param filePath
     *            绝对路径
     * @return
     */
    public static String getFolder(String filePath) {
        if (filePath == null || filePath.trim().length() == 0) {
            return null;
        }
        int i = filePath.lastIndexOf("/");
        int j = filePath.lastIndexOf("\\");
        int index = i > j ? i : j;
        return filePath.substring(0, index);
    }
    
    /**
	 * 删除指定文件夹及其下文件
	 * 
	 * @param folder
	 * @return
	 */
    public static boolean deleteFiles(File folder) {
		if (folder == null || !folder.exists()) {
			return true;
		} else if (folder.isFile()) {
			File parent = folder.getParentFile();
			boolean result = folder.delete();
			if (result && null != parent && parent.listFiles().length == 0) {
				return parent.delete();
			}
			return result;
		} else {
			File[] files = folder.listFiles();
			if (files == null || files.length == 0) {
				return folder.delete();
			}

			for (File file : files) {
				if (!deleteFiles(file)) {
					return false;
				}
			}
			return folder.delete();
        }
    }
   
    public static String getUri(String origPath) {
    	try {
			return Uri.fromFile(new File(origPath)).toString();
		} catch (Exception e) {
			return null;
		}
    }
    
    public static void createFolder(String folderPath) {
        File folder = new File(FileTools.getFolder(folderPath));
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
    
}
