package com.college.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.studentmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByuserName(String userName);
	
	boolean existsUserByuserName(String userName);
	
}
