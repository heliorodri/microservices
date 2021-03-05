package com.heliorodri.cloud.tema7.connection.utils;

public enum InfoApi {
    TWITTER_URL("http://localhost:8081/rest/tweetsTotal/"),
    GITHUB_URL("http://localhost:8082/rest/reposTotal/");

    private String url;

    InfoApi(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
