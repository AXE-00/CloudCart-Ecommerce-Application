package com_selfProject_UserAuth.repository;

import com_selfProject_UserAuth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<User,String> {
    User findByEmailAndPassword(String email, String password);
}
