package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author sunyukun
 * @since 2019/12/5 14:01
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).run(args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
