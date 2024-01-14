package hackeru.noameil.petfoodstore.repository;

import hackeru.noameil.petfoodstore.entity.Category;
import hackeru.noameil.petfoodstore.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
