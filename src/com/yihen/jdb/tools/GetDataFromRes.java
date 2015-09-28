package com.yihen.jdb.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import android.content.Context;

/**
 * 
 * @author Frosty
 * @date 2015-4-27 下午4:00:25
 * @description  获取资源文件数据
 *
 */
public  class GetDataFromRes{
	
	/*资源文件中读取字符串*/
	public static String getStr(Context context, int resId)
	{
		return context.getResources().getString(resId);
	}
	
	/*从xml文件读取字符串数据数据*/
	public static String[] getStrArray(Context context,int resId)
	{
		return context.getResources().getStringArray(resId);
	}
	
	/*读取属性文件的数据集合*/
	@SuppressWarnings("unchecked")
	public static Map<String,String> loadProperties(Context context, String fileName)
	{
		Map<String,String> propertiData = new HashMap<String	, String>();
		Properties  property = new Properties();
		InputStream is = context.getClassLoader().getResourceAsStream(fileName);
		try {
			property.load(is);
			
			Enumeration<String> en = (Enumeration<String>) property.propertyNames();
			
			while(en.hasMoreElements()){
				String key = en.nextElement();
				String value = property.getProperty(key);
				propertiData.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return propertiData;
	}
	
	
	/*将数组转换成list<Map<String,Object>>集合*/
	public static List<Map<String,Object>> convertArr2ListMap(String[] keys,int[] sourceId,String[] sourceText ){
		
		int num = Math.min(sourceId.length, sourceText.length);
		
		if(num <=0 ){
			return null;
		}
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i = 0; i<num; i++){
			Map<String,Object>  obj = new HashMap<String, Object>();
			obj.put(keys[0], sourceId[i]);
			obj.put(keys[1], sourceText[i]);
			list.add(obj);
		}
		return list;
	}
	
}
