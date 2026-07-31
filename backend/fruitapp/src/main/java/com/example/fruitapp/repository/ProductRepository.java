package com.example.fruitapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.fruitapp.entity.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // flashsale
    @Query("""
            SELECT p
            FROM Product p JOIN FlashSaleItem fis ON p.id = fis.product.id
            JOIN FlashSale fs ON fis.flashSale.id = fs.id
            WHERE fs.isActive = true AND CURRENT_TIMESTAMP BETWEEN fs.startTime AND fs.endTime
            """)
    List<Product> getFlashSaleProducts();

    // best seller
    @Query("""
            SELECT oi.product.id, SUM(oi.quantity) as total_quantity
            FROM OrderItem oi
            JOIN oi.order o
            WHERE o.status = 'COMPLETED'
            GROUP BY oi.product.id
            ORDER BY total_quantity DESC
            """)
    List<Object[]> findBestSellerIds();

    //10 product new
    List<Product> findTop10ByIsActiveTrueOrderByCreatedAtDesc();

    //10 product hot(rcm)
    List<Product> findTop10ByIsActiveTrueOrderByIdDesc();

    List<Product> findByIdIn(List<Integer> ids);

    List<Product> findTop10ByCategoryIdAndIsActiveTrue(Integer categoryId);

}
