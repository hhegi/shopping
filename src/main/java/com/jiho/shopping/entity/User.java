package com.jiho.shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nick;
    @Column(unique = true)
    private String username;
    private String password;
    private String phoneNum;
    private String item;
    private LocalDateTime createdAt;

    private String role;

    @PrePersist
    public void createDate(){
        this.createdAt = LocalDateTime.now();
    }
}
