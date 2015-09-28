package com.yihen.jdb.net.param;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.task.Priority;
import org.apache.http.Header;

import java.util.List;

/**
 * @author Yuanbin
 * @ProjectName: jdb_app
 * @Description:
 * @date 15-2-15 上午10:32
 */
public class BaseParamRequest<T> {

      private HttpRequest.HttpMethod method;

      private String url;

      private String charset;

      private Priority priority;

      private T body;

      private List<Header> header;

      public BaseParamRequest(){
          this.method = HttpRequest.HttpMethod.GET;
          this.charset = "UTF-8";
      }

    public T getBody() {
        return body;
    }
    public List<Header> getHeader() {
        return header;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getCharset() {
        return charset;
    }

    public BaseParamRequest<T> setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public BaseParamRequest<T> setHeader(List<Header> header) {
        this.header = header;
        return this;
    }

    public BaseParamRequest<T> setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public String getUrl() {
        return url;
      }

      public HttpRequest.HttpMethod getMethod() {
        return method;
      }

      public BaseParamRequest<T> setMethod(HttpRequest.HttpMethod method) {
         this.method = method;
          return this;
      }

     public BaseParamRequest<T> setUrl(String url) {
        this.url = url;
         return this;
      }

     public BaseParamRequest<T> setBody(T body) {
        this.body = body;
        return this;
     }
}
