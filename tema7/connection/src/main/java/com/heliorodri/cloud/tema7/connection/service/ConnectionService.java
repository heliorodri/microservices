package com.heliorodri.cloud.tema7.connection.service;

import com.heliorodri.cloud.tema7.connection.exceptions.CannotExecuteRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${twitterApi}")
    private String twitterApi;

    @Value("${githubApi}")
    private String githubApi;

    private String consumeAPI(String url){
        String result = this.restTemplate.getForObject(url, String.class);

        if (result == null){
            throw new CannotExecuteRequestException("User not found");
        }

        return result;
    }

    public String getGithub(String username){
        return consumeAPI(this.githubApi + username);
    }

    public String getTwitter(String username){
        return consumeAPI(this.twitterApi + username);
    }

    public String getGithubAndTwitter(String userTwitter, String userGithub){
        StringBuilder display = new StringBuilder();

        String gitResult = "";
        String twitterResult = "";

        try {
            gitResult = consumeAPI(this.githubApi + userGithub);
        } catch (CannotExecuteRequestException e){
            display.append("github error: ").append(e.getMessage());
        }

        try {
            twitterResult = consumeAPI(this.twitterApi + userTwitter);
        } catch (CannotExecuteRequestException e){
            display.append("twitter error: ").append(e.getMessage());
        }

        display.append(gitResult).append("\n").append(twitterResult);

        return display.toString();
    }
}

