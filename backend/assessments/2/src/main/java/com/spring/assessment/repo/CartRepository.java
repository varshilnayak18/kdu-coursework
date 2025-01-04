package com.spring.assessment.repo;

import com.spring.assessment.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "select cart from cart c where c.user_user_id = :userId", nativeQuery = true)
    Cart findByUserId(@Param("userId") long userId);
}
