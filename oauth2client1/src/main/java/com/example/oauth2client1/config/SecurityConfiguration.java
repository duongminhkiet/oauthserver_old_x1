package com.example.oauth2client1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests()
            .antMatchers("/", "/login**").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login();
    }
//	@Bean
//	public ClientRegistrationRepository clientRegistrationRepository() {
//		return new InMemoryClientRegistrationRepository(this.customClientRegistration());
//	}
//
// 	private ClientRegistration customClientRegistration() {
// 		return ClientRegistration.withRegistrationId("custom-provider")
// 			.clientId("R2dpxQ3vPrtfgF72")
// 			.clientSecret("fDw7Mpkk5czHNuSRtmhGmAGL42CaxQB9")
// 			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)//basic
// 			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)//authorization_code
// 			.redirectUri("http://localhost:8082/login/oauth2/code/")
//// 			.scope("openid", "profile", "email", "address", "phone")
// 			.scope("user_info")
// 			.authorizationUri("http://localhost:8081/authx/oauth/authorize")
// 			.tokenUri("http://localhost:8081/authx/oauth/token")
// 			.userInfoUri("http://localhost:8081/authx/user/me")
// 			.userNameAttributeName(IdTokenClaimNames.SUB)//name
//// 			.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
// 			.clientName("Auth Server")
// 			.build();
//	}
 	 
}
