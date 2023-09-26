package com.project.ownote.emp.login.service;

import com.project.ownote.emp.EmpMapper;
import com.project.ownote.emp.login.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private EmpMapper empMapper;

    public LoginDto selectByEmail(String emp_email) {
        return empMapper.selectByEmail(emp_email);
    }
}
