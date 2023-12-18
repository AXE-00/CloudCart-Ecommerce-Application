package com_selfProject_UserAuth.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
@Id
    private String userEmail;
    private String userName;
    private String password;
    private String role;
    private long phoneNo;
    private String imageName;
}
