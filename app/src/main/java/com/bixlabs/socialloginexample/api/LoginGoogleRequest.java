package com.bixlabs.socialloginexample.api;

public class LoginGoogleRequest {
    private String tokenId;
    private String serverAuth;

    public LoginGoogleRequest(String tokenId, String serverAuth) {
        this.tokenId = tokenId;
        this.serverAuth = serverAuth;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getServerAuth() {
        return serverAuth;
    }

    public void setServerAuth(String serverAuth) {
        this.serverAuth = serverAuth;
    }
}
