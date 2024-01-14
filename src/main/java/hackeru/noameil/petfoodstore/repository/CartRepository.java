package hackeru.noameil.petfoodstore.repository;

import hackeru.noameil.petfoodstore.entity.Cart;
import hackeru.noameil.petfoodstore.entity.Item;
import hackeru.noameil.petfoodstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
