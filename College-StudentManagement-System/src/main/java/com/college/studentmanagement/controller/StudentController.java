package com.college.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.studentmanagement.dto.StudentDto;
import com.college.studentmanagement.entity.Student;
import com.college.studentmanagement.service.StudentService;

@RestController
@RequestMapping(path="/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping(path="/list")
	public List<Student> fetchAllStudents(){
		return studentService.fetchAll(); 
	}

    @GetMapping(path="/list/{id}")
    public Student fetchStudent(@PathVariable("id") int id) {
    	return studentService.fetchById(id);
    }
    
    @PostMapping(path="/add")
    public Student addStudent(@RequestBody StudentDto studentDto) {
    	return studentService.addStudent(studentDto);
    }

    @PutMapping(path="/{id}")
    public Student updateStudent(@PathVariable("id") int id,@RequestBody StudentDto studentDto) {
    	return studentService.updateStudent(studentDto, id);
    }
    
    @DeleteMapping(path="/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
    	return studentService.deleteStudent(id);
    }
}
