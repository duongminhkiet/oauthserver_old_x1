package com.example.oauth2server1;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import com.example.oauth2server1.entity.Clients;
import com.example.oauth2server1.repo.ClientRepository;

@SpringBootApplication
@EnableResourceServer
public class Oauth2server1Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Oauth2server1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createTest();
	}

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	ClientRegistrationService clientRegistrationService;
	private void createTest() {
//		Set<String> set3 = Set.<String>of("val1", "val2", "val3");
		Clients clientX = new Clients();
		clientX.setAuthorizedGrantTypes("authorization_code");
		clientX.setScopes("user_info");
		clientX.setAutoApprove(true);
		clientX.setClientid("R2dpxQ3vPrtfgF72");
		clientX.setClientSecret(passwordEncoder.encode("fDw7Mpkk5czHNuSRtmhGmAGL42CaxQB9"));
		clientX.setRedirectUris("http://localhost:8082/login/oauth2/code/");
		clientRepository.save(clientX);
		
        final BaseClientDetails baseClientDetails =  new BaseClientDetails();//String clientId, String resourceIds,String scopes, String grantTypes, String authorities
        baseClientDetails.setClientId(clientX.getClientid());
        baseClientDetails.setClientSecret(clientX.getClientSecret());
        baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(clientX.getAuthorizedGrantTypes()));
        baseClientDetails.setScope(Arrays.asList(clientX.getScopes()));
        baseClientDetails.setRegisteredRedirectUri(Collections.singleton(clientX.getRedirectUris()));
        baseClientDetails.setAutoApproveScopes(Collections.singleton("true"));
        baseClientDetails.setResourceIds(null);
        clientRegistrationService.addClientDetails(baseClientDetails);
	}
}
