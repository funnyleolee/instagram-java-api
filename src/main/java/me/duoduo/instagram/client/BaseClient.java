package me.duoduo.instagram.client;

import java.util.HashMap;
import java.util.Map;

import me.duoduo.instagram.util.HttpClientUtil;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class BaseClient {
    

    private String api_url;
    private String client_id;
    private String client_secret;
    private String redirect_url;
   
    public Map<String, Object> basic_params(String access_token) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(access_token)) {
            params.put("access_token", access_token);
        } else {
            params.put("client_id", client_id);
        }
        return params;
    }

    protected Map<String, Object> get(String path, Map<String, Object> params) throws Exception {
        return parseResult(HttpClientUtil.get(api_url + path, params));
    }

    protected Map<String, Object> post(String path, Map<String, Object> params) throws Exception {
        return parseResult(HttpClientUtil.post(api_url + path, params));
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> parseResult(String str) {
        Map<String, Object> map = JSON.parseObject(str, Map.class);
        return map;
    }

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }
    
}
