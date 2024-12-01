package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@SpringBootApplication
public class CmsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsConsumerApplication.class, args);
		

        Client client = ClientBuilder.newClient();
        String loginUrl = "http://localhost:8084/service/users/login";

        String loginRequestJson = "{\"login\":\"admin\",\"password\":\"admin\"}"; 

        Response response = client.target(loginUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(loginRequestJson, MediaType.APPLICATION_JSON));

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            System.out.println("Login successful");
        } else if (response.getStatus() == Response.Status.UNAUTHORIZED.getStatusCode()) {
            System.out.println("Invalid password");
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            System.out.println("User not found");
        } else {
            System.out.println("Error");
        }

        
        client.close();
		
	}

}
