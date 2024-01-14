package hackeru.noameil.petfoodstore.dto.SubCategory;

import hackeru.noameil.petfoodstore.dto.Category.CategoryMiniDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemMiniDto;
import hackeru.noameil.petfoodstore.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryResponseDto {

    private Long SubCategoryId;
    private String subCategoryName;
    private CategoryMiniDto category;
    private Set<ItemMiniDto> items;

}
