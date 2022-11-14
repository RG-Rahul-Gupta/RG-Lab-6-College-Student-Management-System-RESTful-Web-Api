package com.college.studentmanagement.dto;

import com.college.studentmanagement.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private int user_id;
	private String userName;
	private String password;
	private Role role;
}
