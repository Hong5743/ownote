package com.project.ownote.attendance.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class AttendanceDto {

    @Id
    private Long attendance_id;
    private Date att_date;
    private Timestamp att_ontime;
    private Time att_offtime;
    private String att_status;
    private String emp_name;
    private String att_gradename;
    private String att_deptname;
}
