package me.duoduo.instagram.client;

import java.util.Map;

public class Media extends BaseClient {
    
    public Map<String, Object> media_search(double lat, double lng) throws Exception{
        return media_search(lat, lng, null);
    }
    
    public Map<String, Object> media_popular() throws Exception{
        return media_popular(null);
    }
    
    public Map<String, Object> media_search(double lat, double lng, String access_token) throws Exception {
        Map<String, Object> params = basic_params(access_token);
        params.put("lat", lat);
        params.put("lng", lng);
        return get("media/popular", params);
    }

    public Map<String, Object> media_popular(String access_token) throws Exception {
        return get("media/popular", basic_params(access_token));
    }
}
