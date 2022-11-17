package com.erensayar.cocauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.erensayar.core"})
public class CocAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CocAuthServerApplication.class, args);
    }

}
