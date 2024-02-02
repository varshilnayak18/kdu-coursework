package com.spring.assessment.repo;

import com.spring.assessment.entity.Cart;
import com.spring.assessment.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct,Long> {
    @Query(value = "select c from cart_product c where c.cart_cart_id = :cartId", nativeQuery = true)
    List<CartProduct> findByCartId(@Param("cartId") long cartId);

    @Query(value = "update cart_product c set c.product_quantity = :quantity WHERE c.product_inventory_product_id = :productId AND c.cart_cart_id = :cartId", nativeQuery = true)
    void updateProduct(@Param("cartId") long cartId,@Param("productId") long productId,@Param("quantity") long quantity);

    @Query(value = "delete from cart_product where cart_cart_id= :cartId AND product_inventory_product_id= :productId", nativeQuery = true)
    void deleteProduct(@Param("cartId")long cartId, @Param("productId")long productId);

}
