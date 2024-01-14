package hackeru.noameil.petfoodstore.dto.Category;

import hackeru.noameil.petfoodstore.entity.OrderItem;
import hackeru.noameil.petfoodstore.validators.Category.UniqueCategoryName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestDto {

    @NotNull
    @UniqueCategoryName
    private String categoryName;

    private Set<Long> items;

    private Set<Long> subCategories;

}
