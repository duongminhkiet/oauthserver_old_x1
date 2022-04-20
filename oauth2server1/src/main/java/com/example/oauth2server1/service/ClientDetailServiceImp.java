package com.example.oauth2server1.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.example.oauth2server1.entity.Clients;
import com.example.oauth2server1.repo.ClientRepository;
@Service
public class ClientDetailServiceImp implements ClientDetailsService, ClientRegistrationService {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		Clients clientX = clientRepository.findByClientid(clientId);
		if(clientId == null) {
			throw new ClientRegistrationException("not found clientId");
		}
//        BaseClientDetails details = new BaseClientDetails();
//        details.setClientId(clientId);
//        details.setAuthorizedGrantTypes(Arrays.asList("authorization_code") );
//        details.setScope(Arrays.asList("read, trust"));
//        details.setResourceIds(Arrays.asList("oauth2-resource"));
//        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
//        details.setAuthorities(authorities);
        
      BaseClientDetails baseClientDetails =  new BaseClientDetails();//String clientId, String resourceIds,String scopes, String grantTypes, String authorities
      baseClientDetails.setClientId(clientX.getClientid());
      baseClientDetails.setClientSecret(passwordEncoder.encode(clientX.getClientSecret()));
      baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(clientX.getAuthorizedGrantTypes()));
      baseClientDetails.setScope(Arrays.asList(clientX.getScopes()));
      baseClientDetails.setRegisteredRedirectUri(Collections.singleton(clientX.getRedirectUris()));
//      Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//      authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
//      baseClientDetails.setAuthorities(authorities);
      baseClientDetails.setAutoApproveScopes(Collections.singleton("true"));
        return baseClientDetails;
	}
	@Override
	public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeClientDetails(String clientId) throws NoSuchClientException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<ClientDetails> listClientDetails() {
		// TODO Auto-generated method stub
		return null;
	}

}
