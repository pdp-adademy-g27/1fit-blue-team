package com.example.onefit.friend.entity;

import com.example.onefit.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`friend`")
public class Friend {
    @Id
    private UUID friendId;
    private UUID userId;

    @ManyToOne
    @JoinColumn(name="userId", insertable=false, updatable=false)
    private User user;
}