package com.project.ownote.emp.login.controller;

import com.project.ownote.emp.login.dto.LoginDto;
import com.project.ownote.emp.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/emp/logIn", method = RequestMethod.GET)
    public String getLogin() {
        return "/emp/loginForm";
    }

    @RequestMapping(value = "/emp/loginSucess", method = RequestMethod.POST)
    public String PostLogin(@RequestParam String emp_email, @RequestParam String emp_password, HttpSession session) {
        String email = emp_email + "@ownote.com";
        LoginDto loginDto = loginService.selectByEmail(email);
        if(loginDto != null && loginDto.getEmp_password().equals(emp_password)){
            session.setAttribute("loggedInUser", loginDto);
            session.setMaxInactiveInterval(3600);
            return "/emp/loginSucess";
        } else {
            return "redirect:/emp/logIn";
        }
    }
}
