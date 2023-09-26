package com.project.ownote.emp.login.dto;

import com.project.ownote.emp.login.exception.WrongIdPasswordException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private int emp_id;
    private int emp_num;
    private String emp_password;
    private String emp_name;
    private String emp_birth;
    private String emp_email;
    private String emp_phone;
    private Date emp_date;
    private int grade_num;
    private int dept_num;

//    public Emp(String id, String password, String name, String nickname, String phone, LocalDateTime regdate) {
//        super();
//        this.id = id;
//        this.password = password;
//        this.name = name;
//        this.nickname = nickname;
//        this.phone = phone;
//        this.registerDateTime = regdate;
//    }
    public void changePassword(String oldPassword, String newPassword) {
        if (!emp_password.equals(oldPassword))
            throw new WrongIdPasswordException();
        this.emp_password = newPassword;
    }

    public boolean matchPassword(String password) {
        return this.emp_password.equals(password);}
}
