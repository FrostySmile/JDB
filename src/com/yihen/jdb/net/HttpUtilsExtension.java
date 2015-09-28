package com.yihen.jdb.net;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yihen.jdb.bean.ViewModel.Result;
import com.yihen.jdb.net.param.BaseParamRequest;
import com.yihen.jdb.tools.JsonConverter;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:20
 */
public class HttpUtilsExtension extends HttpUtils {

    public HttpUtilsExtension(){
        super();
    }
    /**
     * xutils扩展http异步请求
     * @param baseParamRequest
     * @param callBack
     * @param <TOut>
     * @param <TIn>
     * @return
     */
    public <TOut,TIn> HttpHandler<TOut> send(BaseParamRequest<TIn> baseParamRequest,RequestCallBack<TOut> callBack){
        if(baseParamRequest.getMethod()== HttpRequest.HttpMethod.GET){
            baseParamRequest.setUrl(parseUrl(baseParamRequest));
            baseParamRequest.setBody(null);
        }
        RequestParams requestParams = this.parseRequestParams(baseParamRequest);
        return super.send(baseParamRequest.getMethod(),baseParamRequest.getUrl(),requestParams,callBack);
    }

    /**
     * xutils扩展http同步请求
     * @param baseParamRequest
     * @param <TOut>
     * @param <TIn>
     * @return
     * @throws com.lidroid.xutils.exception.HttpException
     */
    public <TOut,TIn> Result<TOut> sendSync(BaseParamRequest<TIn> baseParamRequest) throws com.lidroid.xutils.exception.HttpException {
        if(baseParamRequest.getMethod()== HttpRequest.HttpMethod.GET){
            baseParamRequest.setUrl(parseUrl(baseParamRequest));
            baseParamRequest.setBody(null);
        }
        RequestParams requestParams = this.parseRequestParams(baseParamRequest);
        ResponseStream stream =  super.sendSync(baseParamRequest.getMethod(),baseParamRequest.getUrl(),requestParams);
        Result<TOut> out;
        try {
            String json = stream.readString();
            out = JsonConverter.deserialize(json,new TypeToken<Result<TOut>>(){}.getType());
        }catch (IOException e){
            out = null;
        }
        return out;
    }

    private <TIn> String parseUrl(BaseParamRequest<TIn> baseParamRequest){
        if(baseParamRequest.getBody() == null) return baseParamRequest.getUrl();

        TIn tIn = baseParamRequest.getBody();

        Field[] fields = tIn.getClass().getDeclaredFields();
        String url = baseParamRequest.getUrl();
        url = url == null ? "" : url+"?";
        for (Field field : fields){
            field.setAccessible(true);
            try {
                Class<?> c = field.getDeclaringClass();
                Object value = field.get(tIn);
                if(!c.isPrimitive()&& !value.getClass().isAssignableFrom(String.class)){
                    continue;
                }
                url += field.getName()+"="+value+"&";
            }catch (Exception e){
                url += field.getName()+"= &";
            }
        }
        url = url.substring(0,url.length()-1);
        return url;
    }
    /**
     * 参数解析
     * @param baseParamRequest
     * @param <TIn>
     * @return
     */
    @SuppressWarnings("unchecked")
	private <TIn> RequestParams parseRequestParams(BaseParamRequest<TIn> baseParamRequest){
        //设置字符集
        RequestParams requestParams = new RequestParams(baseParamRequest.getCharset());
        //设置header
        if(baseParamRequest.getHeader()!=null)
        requestParams.setHeaders(baseParamRequest.getHeader());
        //设置优先级
        if(baseParamRequest.getPriority()!=null)
        requestParams.setPriority(baseParamRequest.getPriority());
        //设置bodyParams
        TIn tIn = baseParamRequest.getBody();
        if(tIn==null) return requestParams;
        try {
            String json = JsonConverter.serialize(tIn);
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()){
                String str = iterator.next();
                requestParams.addBodyParameter(new BasicNameValuePair(str,jsonObject.getString(str)));
            }
        } catch (Exception e) {

        }
        return requestParams;
    }
}
