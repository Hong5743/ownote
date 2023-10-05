package com.project.ownote.attendance.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "attendance")
public class Attendance {
    @Id
    private Long attendance_id;
    private LocalDate att_date;
    private LocalTime att_ontime;
    private LocalTime att_offtime;
    private String att_status;
    private String emp_name;
    private String att_gradename;
    private String att_deptname;
}
