package hackeru.noameil.petfoodstore.dto.SubCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategoryMiniDto {
    private Long subCategoryId;
    private String subCategoryName;
}
