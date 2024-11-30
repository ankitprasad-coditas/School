package com.project.School.utility;

import com.project.School.dto.StudentDto;
import com.project.School.entity.Course;
import com.project.School.entity.Student;
import com.project.School.exceptions.NotFoundException;
import com.project.School.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UpdateStudent {

    @Autowired
    private CourseRepo courseRepo;

    public Student updateStudentDetails(Student student, StudentDto studentDto){

        Set<Course> enrolledCourses = student.getEnrolledCourses();

        if(studentDto.getName()!=null){
            student.setName(studentDto.getName());
        }

        if(studentDto.getAge()!=null){
            student.setAge(studentDto.getAge());
        }

        if(studentDto.getStandard()!=null){
            student.setStandard(studentDto.getStandard());
        }

        if(!studentDto.getCourseId().isEmpty()){

            /*studentDto.getCourseId().stream()
                    .map(courseId -> {
                        Course theCourse = courseRepo.findById(courseId).orElseThrow(()-> new NotFoundException("Course not Found!!"));
                        enrolledCourses.add(theCourse);
                    }).collect(Collectors.toSet());*/

            Course theCourse;
            for(Long id : studentDto.getCourseId()){
                theCourse = courseRepo.findById(id).orElseThrow(()-> new NotFoundException("Course not Found!!"));
                enrolledCourses.add(theCourse);
            }
            student.setEnrolledCourses(enrolledCourses);
        }

        return student;
    }
}
