/**   
* @Title: Result.java 
* @Package com.yihen.jdb.bean 
* @Description: TODO
* @author frosty
* @date 2015-4-30 下午4:26:16 
* @version V1.0   
*/
package com.yihen.jdb.bean;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.yihen.jdb.app.AppException;
import com.yihen.jdb.tools.StringTools;

/**
 * @ClassName: Result
 * @Description: 解析server端返回的数据是否有错误
 * @author Frosty
 * @date 2015-4-30 下午4:26:16
 *   
 */
public class Result extends Base{

	private int errorCode;
	private String errorMessage;

	public boolean OK() {
		return errorCode == 1;
	}

	/**
	 * 解析调用结果
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static Result parse(InputStream stream) throws IOException, AppException {
		Result res = null;
		// 获得XmlPullParser解析器
		XmlPullParser xmlParser = Xml.newPullParser();
		try {
			xmlParser.setInput(stream, UTF8);
			// 获得解析到的事件类别，这里有开始文档，结束文档，开始标签，结束标签，文本等等事件。
			int evtType = xmlParser.getEventType();
			// 一直循环，直到文档结束
			while (evtType != XmlPullParser.END_DOCUMENT) {
				String tag = xmlParser.getName();
				switch (evtType) {

				case XmlPullParser.START_TAG:
					// 如果是标签开始，则说明需要实例化对象了
					if (tag.equalsIgnoreCase("result")) 
					{
						res = new Result();
					} 
					else if (res != null) 
					{ 
						if (tag.equalsIgnoreCase("errorCode")) 
						{
							res.errorCode = StringTools.toInt(xmlParser.nextText(), -1);
						} 
						else if (tag.equalsIgnoreCase("errorMessage")) 
						{
							res.errorMessage = xmlParser.nextText().trim();
						}
					}
					break;
				case XmlPullParser.END_TAG:
					//如果遇到标签结束，则把对象添加进集合中
					break;
				}
				// 如果xml没有结束，则导航到下一个节点
				evtType = xmlParser.next();
			}

		} catch (XmlPullParserException e) {
			throw AppException.xml(e);
		} finally {
			stream.close();
		}
		return res;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	@Override
	public String toString(){
		return String.format("RESULT: CODE:%d,MSG:%s", errorCode, errorMessage);
	}

	
}
