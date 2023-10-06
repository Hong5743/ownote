package com.project.ownote.annual.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class AnnualDto {
    private int annual_id;
    private String annual_content;
    private String annual_startdate;
    private String annual_enddate;
    private double anninfo_date;
    private int anninfo_code;
    private String anninfo_name;
    private int anncheck_id;
    private int emp_id;
    private String emp_name;
    private String anncheck_pass;
    private  double annual_time;
    private double anndata_extra;

}
