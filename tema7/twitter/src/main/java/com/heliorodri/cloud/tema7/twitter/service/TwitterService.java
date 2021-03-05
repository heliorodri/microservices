package com.heliorodri.cloud.tema7.twitter.service;

import com.heliorodri.cloud.tema7.twitter.exceptions.CannotExecuteRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

@Service
public class TwitterService {

    @Autowired
    private Twitter twitter;

    public String tweetsTotalByUser(String username) {
        TwitterProfile profile = null;

        if((username == null) || (username.isEmpty())){
            throw new CannotExecuteRequestException("User cannot be null or empty");
        }

        try{
            profile = twitter.userOperations().getUserProfile(username);
        } catch (ResourceNotFoundException e) {
            if (e.getMessage().toUpperCase().contains("USER NOT FOUND")){
                throw new CannotExecuteRequestException("User not found");
            } else {
                throw new CannotExecuteRequestException(e.getMessage());
            }
        }

        return new StringBuilder().append("Total of tweets by ").append(username).append(": ").append(profile.getStatusesCount()).toString();
    }
}
