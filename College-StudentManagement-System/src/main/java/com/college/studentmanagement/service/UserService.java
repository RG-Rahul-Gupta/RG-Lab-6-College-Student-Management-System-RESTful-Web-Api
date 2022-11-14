package com.college.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.studentmanagement.config.DefaultAdminConfig;
import com.college.studentmanagement.dto.UserDto;
import com.college.studentmanagement.entity.Role;
import com.college.studentmanagement.entity.User;
import com.college.studentmanagement.exceptions.UserNameExistException;
import com.college.studentmanagement.repository.RoleRepository;
import com.college.studentmanagement.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private DefaultAdminConfig adminConfig;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void createDefaultAdmin() {
		long countAdmin = roleRepository.countByroleName("ADMIN");
		if (countAdmin == 0) {
			Role role1 = Role.builder().roleName("ADMIN").build();
			Role role2 = Role.builder().roleName("USER").build();
			User user = User.builder().userName(adminConfig.getUserName())
					.password(passwordEncoder.encode(adminConfig.getPassword())).role(role1).build();
			roleRepository.saveAndFlush(role1);
			roleRepository.saveAndFlush(role2);
			userRepository.saveAndFlush(user);
		}

	}

	public User addUser(UserDto userDto) {
		
		String userNameDto = userDto.getUserName();
		boolean flag = userRepository.existsUserByuserName(userNameDto);
		if(flag==false) {
			Role role = roleRepository.findByroleName(userDto.getRole().getRoleName());
			User user = User.builder().userName(userDto.getUserName())
					.password(passwordEncoder.encode(userDto.getPassword())).role(role).build();          
		
			return userRepository.saveAndFlush(user);
		}
		else {
			throw new UserNameExistException("Following UserName Already Exist Please try with Different User Name");
		}	
	}
	
	
	public List<User> fetchAllUsers(){
	   return userRepository.findAll(); 	
	}

}
