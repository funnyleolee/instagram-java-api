package me.duoduo.instagram.client;

import java.util.HashMap;
import java.util.Map;

import me.duoduo.instagram.util.HttpClientUtil;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public abstract class AbstractClient {

    private String apiUrl;
    private String clientId;
    private String clientSecret;
    private String redirectUrl;

    public Map<String, Object> basic_params(String accessToken) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(accessToken)) {
            params.put("access_token", accessToken);
        } else {
            params.put("client_id", clientId);
        }
        return params;
    }

    protected Map<String, Object> get(String path, Map<String, Object> params) throws Exception {
        return parseResult(HttpClientUtil.get(apiUrl + path, params));
    }

    protected Map<String, Object> post(String path, Map<String, Object> params) throws Exception {
        return parseResult(HttpClientUtil.post(apiUrl + path, params));
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> parseResult(String str) {
        Map<String, Object> map = JSON.parseObject(str, Map.class);
        return map;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

}
