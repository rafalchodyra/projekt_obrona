package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.name = :name")
	User getUserByUserName(@Param("name") String name);

	@Query("select u from User u where u.email = :email")
	User getUserByEmail(@Param("email") String email);

	@Query("select u from User u where u.role = :role")
	List <User> getUserByRole(@Param("role")String role);
}
