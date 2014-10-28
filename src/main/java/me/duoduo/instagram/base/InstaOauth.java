package me.duoduo.instagram.base;

import me.duoduo.instagram.client.AbstractClient;

public class InstaOauth extends AbstractClient{
    public String authorizeUrl(){
        getApiUrl().rep+"oauth/authorize/client_id={client_id}&redirect_uri={redirect_uri}&response_type=code";
    }
    private String buildUrl(){
        StringBuilder sbd = new StringBuilder(getApiUrl().replace("v1", "oauth"));
        sbd.append("authorize");
        sbd.append("client_id")
        return sbd.toString();
    }
}
