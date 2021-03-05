package com.heliorodri.cloud.tema7.twitter;

import com.heliorodri.cloud.tema7.twitter.controller.TwitterController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class TwitterApplicationTest {

    @Mock
    private TwitterController controller;

    @Test
    public void shouldGetTotalOfTweetOfOneUser() throws Exception {
        Mockito.doReturn(ResponseEntity.ok("Total of tweets by tema7teste: 5")).when(controller).getTotalTweetsByUser("tema7Teste");

        assertEquals("Total of tweets by tema7teste: 5", controller.getTotalTweetsByUser("tema7Teste").getBody());
        assertEquals(HttpStatus.OK.value(), controller.getTotalTweetsByUser("tema7Teste").getStatusCodeValue());

    }

    @Test
    public void shouldReturnNoContentWhenUserNotFound() {
        Mockito.doReturn(ResponseEntity.noContent().build()).when(controller).getTotalTweetsByUser("user_not_found_test");
        assertEquals(HttpStatus.NO_CONTENT.value(), controller.getTotalTweetsByUser("user_not_found_test").getStatusCodeValue());
    }

}