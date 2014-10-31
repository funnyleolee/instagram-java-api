package me.duoduo.instagram.client;

import java.util.Map;

public class InstaUsers extends InstaClient {
    
    public InstaUsers(){
        super();
    }
    public InstaUsers(String accessToken) {
        super(accessToken);
    }

    public Map<String, Object> user(String userId) throws Exception {
        return get("users/" + userId);
    }

    public Map<String, Object> userFeed() throws Exception {
        return get("users/self/feed");
    }

    public Map<String, Object> userRecentMedia(String userId) throws Exception {
        return get("users/" + userId + "/media/recent");
    }

    public Map<String, Object> userLikedMedia() throws Exception {
        return get("users/self/media/liked", selfParams());

    }

    public Map<String, Object> userSearch(String name) throws Exception {
        Map<String, Object> params = pubParams();
        params.put("q", name);
        return get("users/search", params);
    }
}
