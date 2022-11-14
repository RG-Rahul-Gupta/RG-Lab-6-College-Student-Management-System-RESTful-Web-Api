package com.college.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.studentmanagement.dto.StudentDto;
import com.college.studentmanagement.entity.Student;
import com.college.studentmanagement.exceptions.DataNotFoundException;
import com.college.studentmanagement.repository.StudentRepository;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student addStudent(StudentDto studentDto) {

		Student student = Student.builder().firstName(studentDto.getFirstName()).lastName(studentDto.getLastName())
				.course(studentDto.getCourse()).country(studentDto.getCountry()).build();

		return studentRepository.saveAndFlush(student);
	}

	public List<Student> fetchAll() {
		return studentRepository.findAll();
	}
	
	public Student fetchById(int id) {
		return studentRepository.findById(id).orElseThrow(()->new DataNotFoundException("Record with id " + id + " not found in DataBase"));
	}

	public String deleteStudent(int id) throws DataNotFoundException {
		try {
			studentRepository.deleteById(id);
		} catch (Exception ex) {

			throw new DataNotFoundException("Record with id " + id + " not found in DataBase");
		}

		return "Student with Id -" + id + " deleted from Database";
	}

	public Student updateStudent(StudentDto studentDto, int id) {
		Student studentEntity = studentRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Data Not Found for id -"+id+" in the Database"));

		if (!studentEntity.getFirstName().equals(studentDto.getFirstName())) {
			studentEntity.setFirstName(studentDto.getFirstName());
		}
		
		if (!studentEntity.getLastName().equals(studentDto.getLastName())) {
			studentEntity.setLastName(studentDto.getLastName());
		}
		
		if (!studentEntity.getCourse().equals(studentDto.getCourse())) {
			studentEntity.setCourse(studentDto.getCourse());
		}
		
		if (!studentEntity.getCountry().equals(studentDto.getCountry())) {
			studentEntity.setCountry(studentDto.getCountry());
		}
		

		return studentRepository.saveAndFlush(studentEntity);
	}

}
