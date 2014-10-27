package me.duoduo.instagram.client;



public class Instagram extends Media {

    public Instagram(String api_url, String client_id, String client_cecret, String redirect_url) {
        setApi_url(api_url);
        setClient_id(client_id);
        setClient_secret(client_cecret);
        setRedirect_url(redirect_url);
    }
}
