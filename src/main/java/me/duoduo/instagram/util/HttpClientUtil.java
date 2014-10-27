package me.duoduo.instagram.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
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
        if (params != null) {
            StringBuilder sbd = new StringBuilder();
            for (String name : params.keySet()) {
                String value = URLEncoder.encode(params.get(name).toString(), "UTF-8");
                sbd.append(name).append("=").append(value).append("&");
            }
            if (sbd.length() > 0) {
                url += "?" + sbd.substring(0, sbd.length() - 1);
            }
        }
        HttpGet reqs = new HttpGet(url);
        CloseableHttpResponse resp = getHttpClient().execute(reqs);
        return EntityUtils.toString(resp.getEntity());
    }

    public static String post(String url) throws Exception {
        return post(url, null);
    }

    public static String post(String url, Map<String, Object> params) throws Exception {
        if (params != null) {
            for (String name : params.keySet()) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair(name, params.get(name).toString()));
                nvps.add(new BasicNameValuePair(name, params.get(name).toString()));
            }
        }
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse resp = getHttpClient().execute(post);
        return EntityUtils.toString(resp.getEntity());
    }
}
