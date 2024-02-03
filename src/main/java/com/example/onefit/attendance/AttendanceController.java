package com.example.onefit.attendance;

import com.example.onefit.common.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + AttendanceController.BASE_URL)
@RequiredArgsConstructor
public class AttendanceController {
    public static final String BASE_URL = "/attend";
    private final AttendanceService attendanceService;

    @PostMapping(params = {"userId", "courseId"})
    public ResponseEntity<?> attend(@RequestParam UUID userId, @RequestParam UUID courseId) {
        attendanceService.attend(userId, courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
