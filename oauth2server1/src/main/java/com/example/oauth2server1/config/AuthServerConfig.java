package com.example.oauth2server1.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${user.oauth.clientId}")
    private String ClientID;
    @Value("${user.oauth.clientSecret}")
    private String ClientSecret;
    @Value("${user.oauth.redirectUris}")
    private String RedirectURLs;

   private final PasswordEncoder passwordEncoder;
   private final DataSource dataSource;
   private final ClientDetailsService clientDetailsService;
   @Autowired
    public AuthServerConfig(final DataSource dataSource,PasswordEncoder passwordEncoder, ClientDetailsService clientDetailsService) {
        this.dataSource = dataSource;
		this.passwordEncoder = passwordEncoder;
		this.clientDetailsService = clientDetailsService;
    }
//    @Bean
//    public ClientRegistrationService clientRegistrationService() {
//        return new JdbcClientDetailsService(dataSource);
//    }
//    @Bean("jdbcClientDetailsService")
//    public JdbcClientDetailsService clientDetailsService() {
//        return new JdbcClientDetailsService(dataSource);
//    }
    @Override
    public void configure(
        AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()");
        //.passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
//    	clients.withClientDetails(clientDetailsService);
//    	clients.jdbc(dataSource)//.passwordEncoder(passwordEncoder)
//      .withClient(ClientID)
//      .secret(ClientSecret)
////      .secret(passwordEncoder.encode(ClientSecret))
//      .authorizedGrantTypes("authorization_code")
//      .scopes("user_info")
//      .autoApprove("true")
//      .redirectUris(RedirectURLs).and().build();;
    	
    	
//        clients.inMemory()
//            .withClient(ClientID)
//            .secret(passwordEncoder.encode(ClientSecret))
//            .authorizedGrantTypes("authorization_code")
//            .scopes("user_info")
//            .autoApprove(true)
//            .redirectUris(RedirectURLs);
    }
    @Bean
    public ClientRegistrationService clientRegistrationService() {
        return new JdbcClientDetailsService(dataSource);
    }
//    @Bean("tokenStore")
//    public TokenStore jdbcTokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
//
//    @Bean
//    public ApprovalStore approvalStore() {
//        return new JdbcApprovalStore(dataSource);
//    }

//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }
//    @Bean
//    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }
//  @Autowired
//  private AuthenticationManager authenticationManager;
////    @Autowired
////    private TokenStore tokenStore;
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore())
//                .authenticationManager(authenticationManager);
//    }

//
////    @Autowired
////    private UserDetailsService userDetailsService;
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer enpoints) throws Exception {
//        enpoints.tokenStore(jdbcTokenStore())
//                .approvalStore(approvalStore())
//                .authorizationCodeServices(authorizationCodeServices())
//                .authenticationManager(authenticationManager)
////                .userDetailsService(userDetailsService)
//                ;
//    }
}