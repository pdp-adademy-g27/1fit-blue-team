package com.example.onefit.image;

import com.example.onefit.common.AppConstants;
import com.example.onefit.image.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + ImageController.BASE_URL)
@RequiredArgsConstructor
public class ImageController {
    public static final String BASE_URL = "/image";
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Image> upload(@RequestPart MultipartFile file) throws IOException {
        return ResponseEntity.ok(imageService.uploadImage(file));
    }

    @GetMapping("/{userId}")
    ResponseEntity<byte[]> retrieve(@PathVariable UUID userId) {
        return ResponseEntity.ok(imageService.retrieve(userId));
    }
}
