package hackeru.noameil.petfoodstore.dto.Category;

import hackeru.noameil.petfoodstore.dto.Item.ItemMiniDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemResponseDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryMiniDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {

    private Long categoryId;
    private String categoryName;
    private Set<ItemMiniDto> items;
    private Set<SubCategoryMiniDto> subCategories;

}
