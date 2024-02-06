package com.example.onefit.attendance;

import com.example.onefit.attendance.entity.Attendance;
import com.example.onefit.common.exceptions.ApiException;
import com.example.onefit.course.entity.Course;
import com.example.onefit.course.repository.CourseRepository;
import com.example.onefit.user.entity.User;
import com.example.onefit.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public void attend(UUID courseId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (Objects.isNull(user.getSubscription()))
            throw ApiException.throwException("This user has no subscription");

        LocalDateTime endTime = user.getActivationDate().plusMonths(user.getSubscription().getDuration());
        if (endTime.isBefore(LocalDateTime.now()))
            throw ApiException.throwException("Your subscription is expired");

        Course course = courseRepository.findById(courseId).orElseThrow(EntityNotFoundException::new);
        Attendance attendance = new Attendance(user, course, LocalDateTime.now());
        attendanceRepository.save(attendance);
    }

    public void cancel(UUID courseId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Course course = courseRepository.findById(courseId).orElseThrow(EntityNotFoundException::new);

        if (course.getStartDate().isBefore(LocalDateTime.now()))
            throw ApiException.throwException("The course has already been started");

        attendanceRepository.deleteByUserAndCourse(user, course);
    }
}
