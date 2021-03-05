package com.heliorodri.cloud.tema7.connection;

import com.heliorodri.cloud.tema7.connection.controller.ConnectionController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ConnectionApplicationTest {

    private static final String bothURL = "/rest/twitterAndGithub";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ConnectionController controller;

    @Test
    public void shouldGetTotalOfTweetsOfOneUser() {
        Mockito.doReturn(ResponseEntity.ok("Total of tweets by tema7Teste: 5")).when(controller).getTotalTweets("tema7Teste");
        assertEquals("Total of tweets by tema7Teste: 5", controller.getTotalTweets("tema7Teste").getBody());
        assertEquals(HttpStatus.OK.value(), controller.getTotalTweets("tema7Teste").getStatusCodeValue());
    }

    @Test
    public void shouldGetTotalOfPublicGithubReposOfOneUser() {
        Mockito.doReturn(ResponseEntity.ok("The total of public repositories of heliorodri: 9")).when(controller).getTotalRepos("heliorodri");
        assertEquals("The total of public repositories of heliorodri: 9", controller.getTotalRepos("heliorodri").getBody());
        assertEquals(HttpStatus.OK.value(), controller.getTotalRepos("heliorodri").getStatusCodeValue());
    }

    @Test
    public void shouldGetNoContentWhenGithubUserIsNull() {
        Mockito.doReturn(ResponseEntity.noContent().build()).when(controller).getTotalRepos(null);
        assertEquals(HttpStatus.NO_CONTENT.value(), controller.getTotalRepos(null).getStatusCodeValue());
    }

    @Test
    public void shouldGetNoContentWhenTwitterUserIsNull() {
        Mockito.doReturn(ResponseEntity.noContent().build()).when(controller).getTotalTweets(null);
        assertEquals(HttpStatus.NO_CONTENT.value(), controller.getTotalTweets(null).getStatusCodeValue());
    }

    @Test
    public void shouldGetNoContentWhenGithubUserIsEmpty() {
        Mockito.doReturn(ResponseEntity.noContent().build()).when(controller).getTotalRepos("");
        assertEquals(HttpStatus.NO_CONTENT.value(), controller.getTotalRepos("").getStatusCodeValue());
    }

    @Test
    public void shouldGetNotFoundWhenTwitterUserIsEmpty() {
        Mockito.doReturn(ResponseEntity.noContent().build()).when(controller).getTotalTweets("");
        assertEquals(HttpStatus.NO_CONTENT.value(), controller.getTotalTweets("").getStatusCodeValue());
    }

    @Test
    public void shouldGetNotContentWhenTwitterUserNotFound() {
        Mockito.doReturn(ResponseEntity.noContent().build()).when(controller).getTotalTweets("user_do_not_found_test");
        assertEquals(HttpStatus.NO_CONTENT.value(), controller.getTotalTweets("user_do_not_found_test").getStatusCodeValue());
    }

    @Test
    public void shouldGetNoContentWhenGithubUserNotFound() {
        Mockito.doReturn(ResponseEntity.noContent().build()).when(controller).getTotalRepos("uuserDotNotExist");
        assertEquals(HttpStatus.NO_CONTENT.value(), controller.getTotalRepos("uuserDotNotExist").getStatusCodeValue());
    }

    @Test
    public void shouldGetReposAndTweets() {
        Mockito.doReturn(ResponseEntity.ok("The total of public repositories of heliorodri: 9\nTotal of tweets by tema7teste: 5"))
                .when(controller)
                .getTotalReposAndTweets("heliorodri", "tema7Teste");

        assertEquals("The total of public repositories of heliorodri: 9\nTotal of tweets by tema7teste: 5",
                controller.getTotalReposAndTweets("heliorodri", "tema7Teste").getBody());

        assertEquals(HttpStatus.OK.value(), controller.getTotalReposAndTweets("heliorodri", "tema7Teste").getStatusCodeValue());
    }

    @Test
    public void shouldGetBadRequestWhenGithubUserIsNotInformed() {
        Mockito.doReturn(ResponseEntity.badRequest().build()).when(controller).getTotalReposAndTweets("", "tema7Teste");
        assertEquals(HttpStatus.BAD_REQUEST.value(), controller.getTotalReposAndTweets("", "tema7Teste").getStatusCodeValue());
    }

    @Test
    public void shouldGetBadRequestWhenTwitterUserIsNotInformed() {
        Mockito.doReturn(ResponseEntity.badRequest().build()).when(controller).getTotalReposAndTweets("heliorodri", "");
        assertEquals(HttpStatus.BAD_REQUEST.value(), controller.getTotalReposAndTweets("heliorodri", "").getStatusCodeValue());
    }

    @Test
    public void shouldGetBadRequestWhenNoUserIsInformed() throws Exception {
        Mockito.doReturn(ResponseEntity.badRequest().build()).when(controller).getTotalReposAndTweets("", "");
        assertEquals(HttpStatus.BAD_REQUEST.value(), controller.getTotalReposAndTweets("", "").getStatusCodeValue());
    }
}