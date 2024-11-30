package com.project.School.service;

import com.project.School.dto.AuthRequestDto;
import com.project.School.dto.JwtResponseDto;
import com.project.School.entity.Admin;
import com.project.School.exceptions.DuplicateDataException;
import com.project.School.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public Admin registerAdmin(Admin admin){
        if(adminRepo.findByEmailId(admin.getEmailId()).isPresent()){
            throw new DuplicateDataException("Admin Already Present");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
    }

    public JwtResponseDto adminLogin(AuthRequestDto authRequestDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),authRequestDto.getPassword()));
        if(authentication.isAuthenticated()){

            User admin = (User) authentication.getPrincipal();
            String accessToken = jwtService.generateAccessToken(admin.getUsername());
            String refreshToken = jwtService.generateRefreshToken(admin.getUsername());
            return JwtResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        else{
            throw new IllegalArgumentException("Invalid Login Details");
        }
    }
}
