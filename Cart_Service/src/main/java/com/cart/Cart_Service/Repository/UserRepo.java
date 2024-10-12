package com.cart.Cart_Service.Repository;

import com.cart.Cart_Service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, String> {
    User findByIdAndUserEmail(String id, @Param("userEmail") String userEmail);

}
