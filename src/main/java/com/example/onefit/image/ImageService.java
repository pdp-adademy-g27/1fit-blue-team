package com.example.onefit.image;

import com.example.onefit.common.AppConstants;
import com.example.onefit.image.entity.Image;
import com.example.onefit.user.repository.UserRepository;
import com.example.onefit.user.entity.User;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final UserRepository userRepository;
    private final ImageRepository repository;
    private final Storage storage;

    public Image uploadImage(@RequestPart(name = "file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        BlobId blobId = BlobId.of(AppConstants.BUCKET_NAME, Objects.requireNonNull(fileName));
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getBytes());

        Image image = new Image(UUID.randomUUID(), file.getOriginalFilename(), file.getName(), file.getContentType(), file.getSize());
        image = repository.save(image);
        return image;
    }

    public byte[] retrieve(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        BlobId blobId = BlobId.of(AppConstants.BUCKET_NAME, user.getImage().getOriginalName());
        Blob blob = storage.get(blobId);
        return blob.getContent();
    }
}
