package com.example.oauth2server1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oauth2server1.entity.Clients;
@Repository
public interface ClientRepository extends JpaRepository<Clients, Integer>{
	Clients findByClientid(String clientId);
}
