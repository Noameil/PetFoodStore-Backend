package hackeru.noameil.petfoodstore.dto.SubCategory;

import hackeru.noameil.petfoodstore.entity.Category;
import hackeru.noameil.petfoodstore.entity.Item;
import hackeru.noameil.petfoodstore.validators.Category.UniqueCategoryName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryRequestDto {

    @NotNull
    private String subCategoryName;

    private Long category;

    private Set<Long> items;

}
