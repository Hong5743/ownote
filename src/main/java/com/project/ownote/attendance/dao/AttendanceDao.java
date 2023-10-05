package com.project.ownote.attendance.dao;

import com.project.ownote.attendance.dto.Attendance;
import com.project.ownote.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AttendanceDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AttendanceRepository attendanceRepository;


    public void saveAttendance(Attendance attendance) {
        attendanceRepository.save(attendance);
    }


    public Attendance getAttendanceById(Long attendance_id) {
        return attendanceRepository.findById(attendance_id).orElse(null);
    }


    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }


    public void updateAttendance(Attendance attendance) {
        if (attendanceRepository.existsById(attendance.getAttendance_id())) {
            attendanceRepository.save(attendance);
        }
    }


    public void deleteAttendance(Long attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }
}

