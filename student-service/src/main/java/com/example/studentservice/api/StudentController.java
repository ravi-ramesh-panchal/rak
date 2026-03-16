package com.example.studentservice.api;

import com.example.studentservice.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Students", description = "APIs for managing students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto dto) {
        StudentDto created = studentService.createStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{studentId}")
    @Operation(summary = "Get a student by studentId")
    public ResponseEntity<StudentDto> getStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(studentService.getByStudentId(studentId));
    }

    @GetMapping
    @Operation(summary = "List all students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }
}

