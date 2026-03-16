package com.example.studentservice.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student data transfer object")
public class StudentDto {

    @Schema(description = "Business student identifier", example = "STU-001")
    private String studentId;

    @Schema(description = "Full name of the student", example = "John Doe")
    private String name;

    @Schema(description = "Grade of the student", example = "5")
    private String grade;

    @Schema(description = "Mobile number of the student", example = "9876543210")
    private String mobileNumber;

    @Schema(description = "Name of the school", example = "Springfield Elementary")
    private String schoolName;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}

