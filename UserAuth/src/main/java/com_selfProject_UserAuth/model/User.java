package com_selfProject_UserAuth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    private String id;
    private String userEmail;
    private String userName;
    private String password;
    private String role;
    private long phoneNo;
    private String imageName;

    @PrePersist
    private void generateId() {
        this.id = UUID.randomUUID().toString(); // Generate a new UUID when the entity is persisted
    }
}
