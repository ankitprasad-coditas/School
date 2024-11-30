package com.project.School.controller;

import com.project.School.dto.CourseDto;
import com.project.School.dto.ResponseDto;
import com.project.School.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course/api/v1")
public class CourseController {


    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<ResponseDto<List<CourseDto>>> getAllCourses(){
        ResponseDto<List<CourseDto>> responseDto =new ResponseDto<>(courseService.getAllCourses(),HttpStatus.OK.value(),"All Courses Fetched");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getCourse/{id}")
    public ResponseEntity<ResponseDto<CourseDto>> getCourseById(@PathVariable Long id){
        ResponseDto<CourseDto> responseDto =new ResponseDto<>(courseService.getCourseById(id),HttpStatus.OK.value(),"Course Fetched");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/createNewCourse")
    public ResponseEntity<ResponseDto<CourseDto>> createCourse(@RequestBody CourseDto courseDto){
        ResponseDto<CourseDto> responseDto =new ResponseDto<>(courseService.createNewCourse(courseDto),HttpStatus.CREATED.value(),"Course Created");
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<ResponseDto<CourseDto>> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto){
        ResponseDto<CourseDto> responseDto =new ResponseDto<>(courseService.updateCourse(id,courseDto),HttpStatus.OK.value(),"Course Updated");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        ResponseDto<Void> responseDto =new ResponseDto<>(null,HttpStatus.NO_CONTENT.value(),"Course Deleted");
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
}
