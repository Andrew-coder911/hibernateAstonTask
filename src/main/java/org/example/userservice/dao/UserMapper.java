package org.example.userservice.dao;

import org.example.userservice.service.UserDTO;

import java.time.LocalDateTime;

public final class UserMapper {
    /**
     * Convert UserEntity object to UserDTO object
     * @param userEntity object
     * @return UserDTO
     */
    public static UserDTO toDTO(UserEntity userEntity) {
        Long id = userEntity.getId();
        String name = userEntity.getName();
        String email = userEntity.getEmail();
        int age = userEntity.getAge();
        LocalDateTime createdAt = userEntity.getCreatedAt();
        return new UserDTO(id, name, email, age, createdAt);
    }

    /**
     * Convert UserDTO object to UserEntity object
     * @param userDTO object
     * @return UserEntity
     */
    public static UserEntity toEntity(UserDTO userDTO) {
        return new UserEntity(userDTO.getName(), userDTO.getEmail(), userDTO.getAge());
    }
}
