package com.ics.icsoauth2server.security;

import com.ics.icsoauth2server.oauth2.CustomOauth2RequestFactory;
import com.ics.icsoauth2server.oauth2.provider.UsernamePasswordAuthenticationTokenProvider;
//import com.ics.icsoauth2server.security.filters.CustomTokenEndpointFilter;
import com.ics.icsoauth2server.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.server.authorization.web.OAuth2AuthorizationEndpointFilter;
import org.springframework.security.oauth2.server.authorization.web.OAuth2TokenEndpointFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.security.config.http.SessionCreationPolicy.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDecisionManager accessDecisionManager;
    private final ClientDetailsService clientDetailsService;
//    OAuth2AuthenticationProcessingFilter
//    OAuth2AuthorizationEndpointFilter

//    OAuth2TokenEndpointFilter

    @Bean
    public UsernamePasswordAuthenticationTokenProvider authenticationTokenProvider(){
        UsernamePasswordAuthenticationTokenProvider tokenProvider = new UsernamePasswordAuthenticationTokenProvider();
        return tokenProvider;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder.encode("password"))
//                .roles("AD");
        auth
                .authenticationProvider(
                        authenticationTokenProvider()
                );
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
//                .addFilterBefore(new CustomTokenEndpointFilter(authenticationManagerBean(),new DefaultOAuth2RequestFactory(clientDetailsService)), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .accessDecisionManager(accessDecisionManager)
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/","/error").permitAll();
        }

//    AnonymousAuthenticationProvider
//    PreAuthenticatedAuthenticationProvider
//    FilterChainProxy
//    UsernamePasswordAuthenticationToken
//    RememberMeAuthenticationFilter
//    AccessDecisionVoter
//    AccessDecisionManager
//    LogoutFilter

}
