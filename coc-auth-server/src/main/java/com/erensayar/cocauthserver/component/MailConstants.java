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
public class MailConstants {

    private Mail mail;

    @Getter
    @Setter
    public static class Mail {

        private Confirm confirm;

        @Getter
        @Setter
        public static class Confirm {
            private String subject;
            private String body;
            private String baseLink;
        }

    }


}
