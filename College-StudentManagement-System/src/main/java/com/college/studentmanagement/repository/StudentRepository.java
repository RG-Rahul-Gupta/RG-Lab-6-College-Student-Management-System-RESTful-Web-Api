package com.college.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.studentmanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	
}
