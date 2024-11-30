package com.project.School.controller;

import com.project.School.dto.ResponseDto;
import com.project.School.dto.StudentDto;
import com.project.School.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/api/v1")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<ResponseDto<List<StudentDto>>> getAllStudents(){
        ResponseDto<List<StudentDto>> responseDto =new ResponseDto<>(studentService.getAllStudents(), HttpStatus.OK.value(),"All Students Fetched");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/getStudentsById/{id}")
    public ResponseEntity<ResponseDto<StudentDto>>getAllStudents(@PathVariable Long Id){
        ResponseDto<StudentDto> responseDto =new ResponseDto<>(studentService.getStudentByRollNo(Id), HttpStatus.OK.value(),"Student Details Fetched");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/createNewStudent")
    public ResponseEntity<ResponseDto<StudentDto>> createNewStudent (@RequestBody StudentDto studentDto){
        ResponseDto<StudentDto> responseDto =new ResponseDto<>(studentService.createNewStudent(studentDto), HttpStatus.CREATED.value(),"New Student Enrolled");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateStudentDetails/{id}")
    public ResponseEntity<ResponseDto<StudentDto>> updateStudentDetails(@PathVariable Long id,@RequestBody StudentDto studentDto){
        ResponseDto<StudentDto> responseDto =new ResponseDto<>(studentService.updateStudentDetails(id,studentDto), HttpStatus.OK.value(),"Students Details Updated");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteStudent(@PathVariable Long Id){
        studentService.deleteStudent(Id);
        ResponseDto<Void> responseDto =new ResponseDto<>(null, HttpStatus.OK.value(),"Student Deleted SuccessFully");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}


