package com.example.onefit.user.entity;

import com.example.onefit.role.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private LocalDateTime dateOfBirth;
    private Gender gender;
    @ManyToMany
    @JoinTable(name = "user_friend", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<User> friends;
    @ManyToMany
    private List<Role> roles;
}
