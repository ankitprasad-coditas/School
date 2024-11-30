package com.project.School.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.School.dto.CourseDto;
import com.project.School.entity.Course;
import com.project.School.exceptions.DuplicateDataException;
import com.project.School.exceptions.NotFoundException;
import com.project.School.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepo courseRepo;
    private final ObjectMapper objectMapper;

    @Autowired
    public CourseService(CourseRepo courseRepo, ObjectMapper objectMapper) {
        this.courseRepo = courseRepo;
        this.objectMapper = objectMapper;
    }

    //Create New Course
    public CourseDto createNewCourse(CourseDto courseDto){
        if(courseRepo.findByName(courseDto.getName().toUpperCase()).isPresent()){
            throw new DuplicateDataException("Course Already Present");
        }
        Course newCourse = courseRepo.save(objectMapper.convertValue(courseDto, Course.class));
        return objectMapper.convertValue(newCourse,CourseDto.class);
    }


    //Get All courses
    public List<CourseDto> getAllCourses(){
        List<Course> allCourses = courseRepo.findAll();
        List<CourseDto> allExistingCourses = allCourses.stream()
                .map(course -> {
                    CourseDto courseDto = objectMapper.convertValue(course,CourseDto.class);
                    return courseDto;
                }).collect(Collectors.toList());
        return allExistingCourses;
    }


    //Get course By id
    public CourseDto getCourseById(Long id){
        Course course = courseRepo.findById(id).orElseThrow(()-> new NotFoundException("Course Not Found"));
        return objectMapper.convertValue(course,CourseDto.class);
    }



    //Update Course Details
    public CourseDto updateCourse(Long id, CourseDto courseDto){
        Course theCourse = courseRepo.findById(id).orElseThrow(()-> new NotFoundException("Course Not Found"));

        if(courseDto.getName()!=null){
            theCourse.setName(courseDto.getName());
        }
        Course updatedCourse = courseRepo.save(theCourse);
        return objectMapper.convertValue(updatedCourse,CourseDto.class);
    }


    //Delete Course
    public void deleteCourse(long id){
        Course theCourse = courseRepo.findById(id).orElseThrow(()-> new NotFoundException("Course Not Found"));
        courseRepo.delete(theCourse);
    }

}
