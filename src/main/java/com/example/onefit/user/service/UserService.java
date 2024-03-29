package com.example.onefit.user.service;

import com.example.onefit.common.exceptions.OtpException;
import com.example.onefit.common.service.GenericService;
import com.example.onefit.image.ImageRepository;
import com.example.onefit.image.entity.Image;
import com.example.onefit.user.dto.*;
import com.example.onefit.user.entity.User;
import com.example.onefit.user.otp.OtpRepository;
import com.example.onefit.user.otp.entity.Otp;
import com.example.onefit.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class UserService extends GenericService<User, UUID, UserResponseDto, UserCreateDto, UserUpdateDto> implements UserDetailsService {
    private final UserRepository repository;
    private final OtpRepository otpRepository;
    private final ImageRepository imageRepository;
    private final Class<User> entityClass = User.class;
    private final UserDtoMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected UserResponseDto internalCreate(UserCreateDto userCreateDto) {
        User user = mapper.toEntity(userCreateDto);
        user.setId(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        setImage(userCreateDto, user);

        isPhoneNumberVerified(userCreateDto.getPhoneNumber());

        User saved = repository.save(user);
        return mapper.toResponseDto(saved);
    }

    private void setImage(UserCreateDto userCreateDto, User user) {
        if (Objects.nonNull(userCreateDto.getImageId())) {
            Image image = imageRepository.findById(userCreateDto.getImageId()).orElseThrow(EntityNotFoundException::new);
            user.setImage(image);
        }
    }

    @Override
    protected UserResponseDto internalUpdate(UUID uuid, UserUpdateDto userUpdateDto) {
        return null;
    }

    private void isPhoneNumberVerified(String phoneNumber) {
        Otp otp = otpRepository
                .findById(phoneNumber)
                .orElseThrow(() -> new OtpException.PhoneNumberNotVerified(phoneNumber));

        if (!otp.isVerified()) {
            throw new OtpException.PhoneNumberNotVerified(phoneNumber);
        }
    }

    @Transactional
    public UserResponseDto signIn(UserSignInDto signInDto) {
        User user = repository.findByPhoneNumber(signInDto.getPhoneNumber())
                .orElseThrow(() -> new BadCredentialsException("Username or password is not correct"));

        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Username or password is not correct");
        }

        return mapper.toResponseDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByPhoneNumber(username).orElseThrow(
                () -> new EntityNotFoundException("User with phone number: %s not found".formatted(username))
        );
    }
}
