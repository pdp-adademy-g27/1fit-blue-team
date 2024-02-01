package com.example.onefit.attendance;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/attend")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping(params = {"userId", "courseId"})
    public ResponseEntity<?> attend(@RequestParam UUID userId, @RequestParam UUID courseId) {
        attendanceService.attend(userId, courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
