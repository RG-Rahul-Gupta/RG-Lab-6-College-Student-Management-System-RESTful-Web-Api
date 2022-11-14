package com.college.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.studentmanagement.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	long countByroleName(String roleName);
	
	Role findByroleName(String roleName);


}
