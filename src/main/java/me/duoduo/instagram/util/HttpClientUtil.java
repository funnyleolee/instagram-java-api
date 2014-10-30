package me.duoduo.instagram.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

    private static int HC_CONN_REQ_TIMEOUT = 1000;
    private static int HC_CONN_TIMEOUT = 1000;
    private static int HC_SOCKET_TIMEOUT = 1000;
    private static RequestConfig requestConfig;

    static {
        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(HC_CONN_REQ_TIMEOUT)
                .setConnectTimeout(HC_CONN_TIMEOUT).setSocketTimeout(HC_SOCKET_TIMEOUT).build();
    }

    public static CloseableHttpClient getHttpClient() {
        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    public static String get(String url) throws Exception {
        return get(url, null);
    }

    public static String get(String url, Map<String, Object> params) throws Exception {
        HttpGet reqs = new HttpGet(url);
        String queryStr = EntityUtils.toString(map2entity(params));
        reqs.setURI(new URI(reqs.getURI().toString() + "?" + queryStr));
        CloseableHttpResponse resp = getHttpClient().execute(reqs);
        return EntityUtils.toString(resp.getEntity());
    }

    public static String post(String url) throws Exception {
        return post(url, null);
    }

    public static String post(String url, Map<String, Object> params) throws Exception {
        HttpPost post = new HttpPost(url);
        post.setEntity(map2entity(params));
        CloseableHttpResponse resp = getHttpClient().execute(post);
        return EntityUtils.toString(resp.getEntity());
    }

    public static UrlEncodedFormEntity map2entity(Map<String, Object> params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null) {
            for (String name : params.keySet()) {
                nvps.add(new BasicNameValuePair(name, params.get(name).toString()));
            }
        }
        return new UrlEncodedFormEntity(nvps, Consts.UTF_8);
    }
}
