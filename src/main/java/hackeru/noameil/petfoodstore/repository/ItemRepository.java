package hackeru.noameil.petfoodstore.repository;

import hackeru.noameil.petfoodstore.entity.Item;
import hackeru.noameil.petfoodstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByItemNameIgnoreCase(String itemName);
}
