package com.project.School.repository;

import com.project.School.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    boolean existsByNameAndEmailId(String name, String emailId);
}
