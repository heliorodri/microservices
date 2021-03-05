package com.heliorodri.cloud.tema7.twitter.controller;

import com.heliorodri.cloud.tema7.twitter.exceptions.CannotExecuteRequestException;
import com.heliorodri.cloud.tema7.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @GetMapping("/tweetsTotal/{username}")
    public ResponseEntity<String> getTotalTweetsByUser(@PathVariable String username){
        try {
            return new ResponseEntity<>(this.twitterService.tweetsTotalByUser(username), HttpStatus.OK);
        } catch (CannotExecuteRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }
}
