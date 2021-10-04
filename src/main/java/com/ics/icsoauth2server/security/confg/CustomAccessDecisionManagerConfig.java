package com.ics.icsoauth2server.security.confg;

import com.ics.icsoauth2server.security.CustomAccessDecisionVoter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CustomAccessDecisionManagerConfig {

    private final CustomAccessDecisionVoter accessDecisionVoter;

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        List<AccessDecisionVoter <? extends Object>> decisionVoters = Arrays.asList(
          new AuthenticatedVoter(),
          new RoleVoter(),
          new WebExpressionVoter(),
          accessDecisionVoter
        );
        return new UnanimousBased(decisionVoters);
    }


}
