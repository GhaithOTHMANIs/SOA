package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User_cms;


@Repository
public interface UserRepository extends JpaRepository<User_cms, String>{


	Optional<User_cms> findByLogin(String username);

}
