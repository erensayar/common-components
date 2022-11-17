package com.erensayar.cocauthserver.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "auth.api")
public class AuthConstants {

    private TokenProperty token;

    @Getter
    @Setter
    public static class TokenProperty {
        private String secretKey;
        private int expireTime;
    }

}
