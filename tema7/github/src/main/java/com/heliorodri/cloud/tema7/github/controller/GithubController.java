package com.heliorodri.cloud.tema7.github.controller;

import com.heliorodri.cloud.tema7.github.exceptions.CannotExecuteRequestException;
import com.heliorodri.cloud.tema7.github.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class GithubController {

    @Autowired
    private GithubService githubService;

    @GetMapping("/reposTotal/{username}")
    public ResponseEntity<String> getTotalRepositories(@PathVariable String username){
        try{
            return new ResponseEntity<>(this.githubService.getTotalRepositories(username), HttpStatus.OK);
        } catch (CannotExecuteRequestException e) {
            if (e.getMessage().toLowerCase().contains("user not found")) {
                 return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
