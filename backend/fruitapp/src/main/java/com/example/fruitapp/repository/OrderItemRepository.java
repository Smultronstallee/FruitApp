package com.example.fruitapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.fruitapp.entity.OrderItem;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("""
            SELECT oi.product.id, SUM(oi.quantity) as total_quantity
            FROM OrderItem oi
            JOIN oi.order o
            WHERE o.status = 'COMPLETED'
            GROUP BY oi.product.id
            ORDER BY total_quantity DESC
            """)
    List<Object[]> findBestSellerIds();

    Long countByOrderUserId(Long userId);

    @Query("SELECT oi.product.category.id FROM OrderItem oi WHERE oi.order.user.id = :userId GROUP BY oi.product.category.id ORDER BY COUNT(oi.product.category.id) DESC")
    List<Integer> findTopCategoryIdsByUserId(@Param("userId") Long userId);

    default Integer findTopCategoryIdByUserId(Long userId) {
        List<Integer> categoryIds = findTopCategoryIdsByUserId(userId);
        return categoryIds.isEmpty() ? null : categoryIds.get(0);
    }
}
