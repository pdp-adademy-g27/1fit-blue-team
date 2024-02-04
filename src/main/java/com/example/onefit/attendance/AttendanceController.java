package com.example.onefit.attendance;

import com.example.onefit.common.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + AttendanceController.BASE_URL)
@RequiredArgsConstructor
public class AttendanceController {
    public static final String BASE_URL = "/attend";
    private final AttendanceService attendanceService;

    @PreAuthorize("hasAuthority('HAS_SUBSCRIPTION')")
    @PostMapping(params = "courseId")
    public ResponseEntity<?> attend(@RequestParam UUID courseId) {
        attendanceService.attend(courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAuthority('HAS_SUBSCRIPTION')")
    @DeleteMapping(params = "courseId")
    public ResponseEntity<?> cancel(@RequestParam UUID courseId) {
        attendanceService.cancel(courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
