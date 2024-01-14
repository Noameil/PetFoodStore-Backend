package hackeru.noameil.petfoodstore.repository;

import hackeru.noameil.petfoodstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
