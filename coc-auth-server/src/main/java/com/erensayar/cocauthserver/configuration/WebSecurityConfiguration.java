package com.erensayar.cocauthserver.configuration;

import com.erensayar.cocauthserver.service.AuthEntryPoint;
import com.erensayar.cocauthserver.service.AuthTokenFilter;
import com.erensayar.cocauthserver.service.impl.UserDetailsServiceImpl;
import com.erensayar.core.util.service.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true    // @pre-@post annotations.
        //securedEnabled = true  // @Secured annotation should be enabled.
        //jsr250Enabled = true   // @RoleAllowed annotation.
)
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST_FOR_DEVELOPMENT = {
            "/h2-console/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "api-docs/**",
            "/api/tests/**"
    };
    private static final String[] AUTH_WHITELIST = {

    };

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPoint unauthorizedHandler;
    private final UtilService utilService;

    private String[] returnDevelopmentEndPointWhiteListAccordingToProfile() {
        if (utilService.activeProfileCheck("local") || utilService.activeProfileCheck("default")) {
            return AUTH_WHITELIST_FOR_DEVELOPMENT;
        }
        return new String[0];
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers(returnDevelopmentEndPointWhiteListAccordingToProfile()).permitAll()
                //.antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable(); // For development (H2 DB can be Visible From Browser)
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}