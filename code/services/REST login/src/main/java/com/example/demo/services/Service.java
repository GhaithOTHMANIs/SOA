package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.User_cms;
import com.example.demo.repositories.UserRepository;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/service")
public class Service {
	@Autowired
    private UserRepository userRepository;

    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User_cms getUtilisateur(@PathParam("id") String id) {
        Optional<User_cms> utilisateur = userRepository.findById(id);
        if (utilisateur.isPresent()) {
            return utilisateur.get();
        } else {
            return null;
        }
    }
    
    public static class LoginRequest {
        private String login;  // Changed from username to login
        private String password;

        // Getters and setters
        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest loginRequest) {
	    Optional<User_cms> utilisateurOptional = userRepository.findByLogin(loginRequest.getLogin());
	
	    if (utilisateurOptional.isPresent()) {
	        User_cms utilisateur = utilisateurOptional.get();
	        
	        if (utilisateur.getPassword().equals(loginRequest.getPassword())) { 
	        	return Response.ok("Valid Login").build();
	        } else {
	            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid password").build();
	        }
	    } else {
	        return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
	    }
	}
}
