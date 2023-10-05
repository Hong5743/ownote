package com.project.ownote.attendance.controller;

import com.project.ownote.attendance.dto.Attendance;
import com.project.ownote.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "attendance/admin";
    }

    @GetMapping("/list1")
    public String listAllAttendances(Model model) {
        List<Attendance> allAttendances = attendanceService.getAllAttendances();
        model.addAttribute("allAttendances", allAttendances);
        return "attendance/list";
    }

    @GetMapping("/{attendance_id}")
    public String viewAttendance(@PathVariable("attendance_id") Long attendanceId, Model model) {
        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
        model.addAttribute("attendance", attendance);
        return "attendance/view";
    }

    @GetMapping("/{attendance_id}/edit")
    public String editAttendanceForm(@PathVariable("attendance_id") Long attendanceId, Model model) {
        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
        model.addAttribute("attendance", attendance);
        return "attendance/edit";
    }

    @PostMapping("/save")
    public String saveAttendance(@ModelAttribute Attendance attendance) {
        attendanceService.saveAttendance(attendance);
        return "redirect:/attendance/insertsucess";
    }

    @GetMapping("/{attendance_id}/delete")
    public String deleteAttendance(@PathVariable("attendance_id") Long attendanceId) {
        attendanceService.deleteAttendanceById(attendanceId);
        return "redirect:/attendance/list";
    }
}
