package org.example.userservice.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class User {
    private Long id;
    private String name;
    private String email;
    private int age;
    private LocalDateTime createdAt;

}
