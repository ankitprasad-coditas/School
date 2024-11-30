package com.project.School.controller;

import com.project.School.dto.AuthRequestDto;
import com.project.School.dto.JwtResponseDto;
import com.project.School.dto.ResponseDto;
import com.project.School.entity.Admin;
import com.project.School.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api/v1")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<Admin>> registerAdmin(@RequestBody Admin admin){
        ResponseDto<Admin> response = new ResponseDto<>(adminService.registerAdmin(admin), HttpStatus.CREATED.value(),"Admin Registered Successfully");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<JwtResponseDto>> adminLogin(@RequestBody @Valid AuthRequestDto authRequestDto){
        ResponseDto<JwtResponseDto> response = new ResponseDto<>(adminService.adminLogin(authRequestDto),HttpStatus.OK.value(),"Logged In Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
