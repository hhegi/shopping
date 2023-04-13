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
    private int idx;
    private String nick;
    @Column(unique = true)
    private String id;
    private String pw;
    private String phoneNum;
    private String address;
    private String item;
    private LocalDateTime createdAt;

    private String role;

    @PrePersist
    public void createDate(){
        this.createdAt = LocalDateTime.now();
    }
}
