package com.project.ownote.emp.adress.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptAdressDto {

    private String emp_name;
    private String emp_email; // 필드 이름 수정
    private int dept_num;
    private String dept_name; // deptName 추가
    private String grade_name; // gradeName 추가

    public DeptAdressDto(String emp_name, String emp_email, int dept_num, String dept_name, String grade_name) {
        this.emp_name = emp_name;
        this.emp_email = emp_email;
        this.dept_num = dept_num;
        this.dept_name = dept_name;
        this.grade_name = grade_name;
    }
}
