package com.example.onefit.attendance.entity;

import com.example.onefit.course.entity.Course;
import com.example.onefit.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {
    @EmbeddedId
    private AttendanceId id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Course course;
    @CreatedDate
    private LocalDateTime date;

    public Attendance(User user, Course course, LocalDateTime date) {
        this.id = new AttendanceId(user.getId(), course.getId());
        this.user = user;
        this.course = course;
        this.date = date;
    }

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    private static class AttendanceId implements Serializable {
        @Column(name = "user_id", insertable = false, updatable = false)
        private UUID userId;
        @Column(name = "course_id", insertable = false, updatable = false)
        private UUID courseId;
    }
}
