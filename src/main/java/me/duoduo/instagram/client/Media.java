package me.duoduo.instagram.client;

import java.util.Map;

public class Media extends AbstractClient {
    
    public Map<String, Object> mediaSearch(double lat, double lng) throws Exception{
        return mediaSearch(lat, lng, null);
    }
    
    public Map<String, Object> mediaPopular() throws Exception{
        return mediaPopular(null);
    }
    
    public Map<String, Object> mediaSearch(double lat, double lng, String access_token) throws Exception {
        Map<String, Object> params = basic_params(access_token);
        params.put("lat", lat);
        params.put("lng", lng);
        return get("media/popular", params);
    }

    public Map<String, Object> mediaPopular(String access_token) throws Exception {
        return get("media/popular", basic_params(access_token));
    }
}
