package com.project.School.service;

import com.project.School.entity.Admin;
import com.project.School.exceptions.NotFoundException;
import com.project.School.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepo adminRepo;

    @Autowired
    public CustomUserDetailsService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws NotFoundException {
        Admin admin = adminRepo.findByEmailId(emailId)
                .orElseThrow(() -> new NotFoundException("Admin not found"));

        return new org.springframework.security.core.userdetails.User(
                admin.getEmailId(),
                admin.getPassword(),
                Collections.emptyList()
        );
    }
}
