package hackeru.noameil.petfoodstore.repository;

import hackeru.noameil.petfoodstore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


}
