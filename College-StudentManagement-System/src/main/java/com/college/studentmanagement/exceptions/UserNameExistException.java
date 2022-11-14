package com.college.studentmanagement.exceptions;

public class UserNameExistException extends RuntimeException {

	public UserNameExistException(String message) {
		super(message);
	}

}
