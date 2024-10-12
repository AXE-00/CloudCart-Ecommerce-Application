package com.cart.Cart_Service.Repository;

import com.cart.Cart_Service.Entity.Cart;
import com.cart.Cart_Service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Optional<Cart> findByProductIdAndUserId(Integer productId,String userId);


    @Query(value = "SELECT product_id FROM Cart WHERE user_id = :userId", nativeQuery = true)
    List<Integer> findProductIdByUserId(@Param("userId") String userId);

    @Modifying
    @Query(value = "Update Cart set deleted=1,acitve=0 where product_id=:productId AND user_id=:userId",nativeQuery = true)
    int deleteByProductIdAndUserId(Integer productId, String userId);
}
