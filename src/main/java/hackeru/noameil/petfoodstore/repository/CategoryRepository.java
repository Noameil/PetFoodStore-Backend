package hackeru.noameil.petfoodstore.repository;

import hackeru.noameil.petfoodstore.entity.Category;
import hackeru.noameil.petfoodstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryNameIgnoreCase(String categoryName);

}
