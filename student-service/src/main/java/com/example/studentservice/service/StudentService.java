package com.example.studentservice.service;

import com.example.studentservice.api.StudentDto;
import com.example.studentservice.domain.Student;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentDto createStudent(StudentDto dto) {
        Student student = new Student();
        student.setStudentId(dto.getStudentId());
        student.setName(dto.getName());
        student.setGrade(dto.getGrade());
        student.setMobileNumber(dto.getMobileNumber());
        student.setSchoolName(dto.getSchoolName());
        Student saved = studentRepository.save(student);
        return toDto(saved);
    }

    @Transactional(readOnly = true)
    public StudentDto getByStudentId(String studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id " + studentId));
        return toDto(student);
    }

    @Transactional(readOnly = true)
    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setStudentId(student.getStudentId());
        dto.setName(student.getName());
        dto.setGrade(student.getGrade());
        dto.setMobileNumber(student.getMobileNumber());
        dto.setSchoolName(student.getSchoolName());
        return dto;
    }
}

