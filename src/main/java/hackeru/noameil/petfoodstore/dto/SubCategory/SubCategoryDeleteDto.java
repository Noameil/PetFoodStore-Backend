package hackeru.noameil.petfoodstore.dto.SubCategory;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryDeleteDto {
    private Long subCategoryId;
    private String subCategoryName;
}
