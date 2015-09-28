package com.yihen.jdb.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yihen.jdb.app.AppException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by G_code on 2015/2/15.
 */
public class JsonConverter {

    private static Gson gson;

    static {
         gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    /**
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        }catch (Exception e)
        {
            return null;
        }
    }

    /**
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, Type type) {
        try {
            json=json.trim();
            T t = gson.fromJson(json, type);
            return t;
        }catch (Exception e)
        {
            return  null;
        }
    }

    /**
     *
     * @param json
     * @return
     */
    public static JSONObject deserializeObject(String json) throws AppException{
        JSONObject object = null;
        try {
            object = new JSONObject(json);
        } catch (JSONException e) {
        		if(e instanceof Exception){
        			throw AppException.json(e);
        		}
        }
        return object;
    }

    public static JSONArray deserializeArray(String json) throws AppException{
        JSONArray jsonArray=null;
        try{
            jsonArray=new JSONArray(json);
        }catch (JSONException e){
        	if(e instanceof Exception){
    				throw AppException.json(e);
    			}
        }
        return jsonArray;
    }

    /**
     *
     * @param obj
     * @return
     */
    public synchronized static String serialize(Object obj) {
        return gson.toJson(obj);
    }
}
