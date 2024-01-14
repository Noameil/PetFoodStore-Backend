package hackeru.noameil.petfoodstore.dto.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryMiniDto {
    private Long categoryId;
    private String categoryName;
}
