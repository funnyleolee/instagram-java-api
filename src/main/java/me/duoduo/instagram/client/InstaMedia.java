package me.duoduo.instagram.client;

import java.util.Map;

public class InstaMedia extends InstaClient {
    
    public Map<String, Object> mediaSearch(double lat, double lng, String access_token) throws Exception {
        Map<String, Object> params = pubParams();
        params.put("lat", lat);
        params.put("lng", lng);
        return get("media/popular", params);
    }

    public Map<String, Object> mediaPopular() throws Exception {
        return get("media/popular");
    }
}
