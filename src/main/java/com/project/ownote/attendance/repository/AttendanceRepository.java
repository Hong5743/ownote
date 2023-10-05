package com.project.ownote.attendance.repository;

import com.project.ownote.attendance.dto.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}

