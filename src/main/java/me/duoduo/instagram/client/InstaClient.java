package me.duoduo.instagram.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import me.duoduo.instagram.util.HttpClientUtil;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class InstaClient {

    protected static String API_URL;
    protected static String CLIENT_ID;
    protected static String CLIENT_SECRET;
    protected static String REDIRECT_URL;

    static {
        Properties prop = new Properties();
        InputStream in = InstaClient.class.getResourceAsStream("/instagram.properties");
        try {
            prop.load(in);
            API_URL = prop.getProperty("instagram.api.url");
            CLIENT_ID = prop.getProperty("instagram.client.id");
            CLIENT_SECRET = prop.getProperty("instagram.client.secret");
            REDIRECT_URL = prop.getProperty("instagram.redirect.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String accessToken;

    public InstaClient() {
        super();
    }

    public InstaClient(String accessToken) {
        this.accessToken = accessToken;
    }

    public Map<String, Object> pubParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(accessToken)) {
            params.put("access_token", accessToken);
        } else {
            params.put("client_id", CLIENT_ID);
        }
        return params;
    }

    public Map<String, Object> selfParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", accessToken);
        return params;
    }

    protected Map<String, Object> get(String path) throws Exception {
        return get(path, pubParams());
    }

    protected Map<String, Object> get(String path, Map<String, Object> params) throws Exception {
        return JSON.parseObject(HttpClientUtil.get(API_URL + path, params));
    }

    protected Map<String, Object> post(String path) throws Exception {
        return post(path, pubParams());
    }

    protected Map<String, Object> post(String path, Map<String, Object> params) throws Exception {
        return JSON.parseObject(HttpClientUtil.post(API_URL + path, params));
    }

}
