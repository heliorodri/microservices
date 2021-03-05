package com.heliorodri.cloud.tema7.github;

import com.heliorodri.cloud.tema7.github.controller.GithubController;
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
public class GithubApplicationTest {

    @Mock
    private GithubController controller;

    @Test
    public void shouldGetTotalOfPublicGithubRepositoriesOfOneUser() {
        Mockito.doReturn(ResponseEntity.ok("The total of public repositories of heliorodri: 9")).when(controller).getTotalRepositories("heliorodri");

        assertEquals("The total of public repositories of heliorodri: 9", controller.getTotalRepositories("heliorodri").getBody());
    }

    @Test
    public void shouldGetNoContentWhenUserNotFound() {
        Mockito.doReturn(ResponseEntity.noContent().build()).when(controller).getTotalRepositories("user_not_found_test");
        assertEquals(HttpStatus.NO_CONTENT.value(), controller.getTotalRepositories("user_not_found_test").getStatusCodeValue());
    }

    @Test
    public void shouldGetBadRequestWhenUserIsNotInformed() {
        Mockito.doReturn(ResponseEntity.badRequest().build()).when(controller).getTotalRepositories("");
        assertEquals(HttpStatus.BAD_REQUEST.value(), controller.getTotalRepositories("").getStatusCodeValue());
    }

}