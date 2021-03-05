package com.heliorodri.cloud.tema7.github.service;

import com.heliorodri.cloud.tema7.github.exceptions.CannotExecuteRequestException;
import org.kohsuke.github.GHFileNotFoundException;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GithubService {

    @Autowired
    private GitHub gitHub;

    public String getTotalRepositories(String username) throws IOException {
        GHUser user;

        try {
            user = this.gitHub.getUser(username);
        } catch (GHFileNotFoundException e){
            if (e.getMessage().toUpperCase().contains("GET-A-USER")) {
                throw new CannotExecuteRequestException("User not found");
            } else {
                throw new CannotExecuteRequestException(e.getMessage());
            }

        }

        return new StringBuilder()
                .append("The total of public repositories of ").append(username).append(": ")
                .append(user.getRepositories().size()).toString();
    }
}
