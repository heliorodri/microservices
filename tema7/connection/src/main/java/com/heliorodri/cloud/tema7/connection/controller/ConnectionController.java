package com.heliorodri.cloud.tema7.connection.controller;

import com.heliorodri.cloud.tema7.connection.exceptions.CannotExecuteRequestException;
import com.heliorodri.cloud.tema7.connection.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @GetMapping("/tweets/{username}")
    public ResponseEntity<String> getTotalTweets(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(this.connectionService.getTwitter(username), HttpStatus.OK);
        } catch (CannotExecuteRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/github/{username}")
    public ResponseEntity<String> getTotalRepos(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(this.connectionService.getGithub(username), HttpStatus.OK);
        } catch (CannotExecuteRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/twitterAndGithub")
    public ResponseEntity<String> getTotalReposAndTweets(@RequestParam("usergithub") String usergit, @RequestParam("usertwitter") String usertwitter) {
        try {
            return new ResponseEntity<>(this.connectionService.getGithubAndTwitter(usertwitter, usergit), HttpStatus.OK);
        } catch (CannotExecuteRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
