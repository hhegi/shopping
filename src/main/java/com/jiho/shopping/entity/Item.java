package com.jiho.shopping.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String name;
    private String image;
    private String imageName;
    private String imagePath;
    private String category;
    private int price;
    private String contents;
    @ManyToOne
    @JoinColumn(name="id", insertable = false,updatable = false)
    private User user;
    private String address;
    private LocalDateTime uploadedAt;
}
