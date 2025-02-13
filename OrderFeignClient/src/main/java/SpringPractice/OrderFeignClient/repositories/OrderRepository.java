package SpringPractice.OrderFeignClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import SpringPractice.OrderFeignClient.models.Order;




@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Query("SELECT o FROM Order o WHERE o.userId = :userId")
    List<Order> findOrdersByUserId(@Param("userId") Long userId);

}
