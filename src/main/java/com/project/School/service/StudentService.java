package com.project.School.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.School.dto.StudentDto;
import com.project.School.entity.Course;
import com.project.School.entity.Student;
import com.project.School.exceptions.NotFoundException;
import com.project.School.repository.CourseRepo;
import com.project.School.repository.StudentRepo;
import com.project.School.utility.UpdateStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final ObjectMapper objectMapper;


    @Autowired
    public StudentService(StudentRepo studentRepo, ObjectMapper objectMapper,CourseRepo courseRepo) {
        this.studentRepo = studentRepo;
        this.objectMapper = objectMapper;
        this.courseRepo = courseRepo;
    }

    private UpdateStudent updateStudent = new UpdateStudent();

    // Create A New Student
    public StudentDto createNewStudent(StudentDto studentDto){
        Student studentDetails =  objectMapper.convertValue(studentDto, Student.class);
        if(studentRepo.existsByNameAndEmailId(studentDto.getName(),studentDto.getEmailId())){
            throw new IllegalArgumentException("Student Already Exists");
        }
        Set<Course> enrolledCourses = new HashSet<>();
        if(studentDto.getCourseId()!=null || !studentDto.getCourseId().isEmpty()){
            for(Long courseId : studentDto.getCourseId()){
                Course optionalCourse = courseRepo.findById(courseId).orElseThrow(()-> new NotFoundException("Course Not Found"));
                enrolledCourses.add(optionalCourse);
            }
        }
        studentDetails.setEnrolledCourses(enrolledCourses);
        Student newStudent = studentRepo.save(studentDetails);
        return objectMapper.convertValue(newStudent,StudentDto.class);
    }

    // Get All Students
    public List<StudentDto> getAllStudents(){
        List<Student> allStudents = studentRepo.findAll();
        List<StudentDto> studentList = allStudents.stream()
                .map(student -> {
                    StudentDto eachStudent = objectMapper.convertValue(student,StudentDto.class);
                    return eachStudent;
                }).collect(Collectors.toList());

        return studentList;
    }


    // Get Student by rollNo
    public StudentDto getStudentByRollNo(Long rollNo){
        Student theStudent = studentRepo.findById(rollNo).orElseThrow(()-> new NotFoundException("Student Not Found!!"));
        return objectMapper.convertValue(theStudent,StudentDto.class);
    }



    // Update Student Detail By RollNo
    public StudentDto updateStudentDetails(Long id, StudentDto studentDto){

        Student theStudent = studentRepo.findById(id).orElseThrow(()-> new NotFoundException("Student Not Found"));
        Student updatedStudent = updateStudent.updateStudentDetails(theStudent,studentDto);
        return objectMapper.convertValue(updatedStudent,StudentDto.class);
    }


    // Delete Student
    public void deleteStudent(Long id){
        Student theStudent = studentRepo.findById(id).orElseThrow(()-> new NotFoundException("Student Not Found"));
        studentRepo.delete(theStudent);
    }

}
