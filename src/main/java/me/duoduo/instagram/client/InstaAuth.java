package me.duoduo.instagram.client;

import java.util.HashMap;
import java.util.Map;

import me.duoduo.instagram.util.HttpClientUtil;

import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class InstaAuth extends InstaClient{
    public String authorizeUrl() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("client_id", CLIENT_ID);
        params.put("redirect_uri", REDIRECT_URL);
        params.put("response_type", "code");
        
        String str = EntityUtils.toString(HttpClientUtil.map2entity(params));
        StringBuilder sbd = new StringBuilder(CLIENT_ID.replace("/v1", "/oauth/authorize"));
        sbd.append("?").append(str);
        return sbd.toString();
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getAccessToken(String code) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("client_id", CLIENT_ID);
        params.put("client_secret", CLIENT_SECRET);
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", REDIRECT_URL);
        params.put("code", code);
        String result = HttpClientUtil.post(CLIENT_ID.replace("/v1", "/oauth/access_token"), params);
        return JSON.parseObject(result, Map.class);
    }
}
