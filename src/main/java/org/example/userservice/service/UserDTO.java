package org.example.userservice.service;

import java.time.LocalDateTime;
import java.util.Objects;

public final class UserDTO {
    private Long id;
    private String name;
    private String email;
    private int age;
    private LocalDateTime createdAt;

    public UserDTO(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public UserDTO(Long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public UserDTO(Long id, String name, String email, int age, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return age == userDTO.age && Objects.equals(name, userDTO.name) && Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, age);
    }
}
