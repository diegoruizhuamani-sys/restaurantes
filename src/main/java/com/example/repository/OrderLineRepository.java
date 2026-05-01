package com.example.repository;

import com.example.model.OrderLine;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findByOrder_Id(Long id);
    @Query("""

SELECT SUM( ol.quantity * ol.dish.price)
            FROM OrderLine ol where ol.order.id =?1
""")

    Double calculateTotalPrice(Long orderId);


}

