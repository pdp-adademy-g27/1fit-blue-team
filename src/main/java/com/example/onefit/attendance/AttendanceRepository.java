package com.example.onefit.attendance;

import com.example.onefit.attendance.entity.Attendance;
import com.example.onefit.course.entity.Course;
import com.example.onefit.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    void deleteByUserAndCourse(User user, Course course);
}
