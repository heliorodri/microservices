package com.heliorodri.cloud.tema7.github.config;

import com.heliorodri.cloud.tema7.github.GithubApplication;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ComponentScan(basePackages = "com.heliorodri.cloud.tema7")
public class GithubConfig {

    @Value("${spring.social.github.token}")
    private String token;

    @Value("${spring.social.github.login}")
    private String login;

    @Bean
    public GitHub gitHub() throws IOException {
        return GitHub.connect(login, token);
    }

}
