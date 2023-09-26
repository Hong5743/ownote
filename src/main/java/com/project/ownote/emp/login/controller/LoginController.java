package com.project.ownote.emp.login.controller;

import com.project.ownote.emp.login.dto.AuthInfo;
import com.project.ownote.emp.login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {



    @Autowired
    AuthService authService;

    @RequestMapping(value = "/emp/logIn", method = RequestMethod.GET)
    public String getLogin() {
        return "/emp/loginForm";
    }

    @RequestMapping(value = "/emp/loginSucess", method = RequestMethod.POST)
    public String PostLogin(@RequestParam("emp_email") String emp_email, @RequestParam("emp_password") String emp_password, HttpSession session) {
        String email = emp_email + "@ownote.com";

        AuthInfo authInfo = authService.authenticate(email, emp_password);
        System.out.println("------------------------------" + authInfo);
        session.setAttribute("authInfo", authInfo);

    return "/emp/loginSucess";
    }
}
