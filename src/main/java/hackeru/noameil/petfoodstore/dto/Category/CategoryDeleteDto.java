package hackeru.noameil.petfoodstore.dto.Category;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDeleteDto {
    private Long categoryId;
    private String categoryName;
}
