package hackeru.noameil.petfoodstore.dto.Item;

import hackeru.noameil.petfoodstore.dto.Category.CategoryMiniDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryMiniDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDto {

    private Long itemId;

    private String itemName;

    private Double cost;

    private String imgURL;

    private Set<CategoryMiniDto> categories;

    private Set<SubCategoryMiniDto> subCategories;

}
