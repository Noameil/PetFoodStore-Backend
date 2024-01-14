package hackeru.noameil.petfoodstore.dto.Item;

import hackeru.noameil.petfoodstore.validators.Item.UniqueItemName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequestDto {

    @NotNull
    @Size(min = 2, max = 255)
    private String itemName;

    @NotNull
    private Double cost;

    private String imgURL;

    @NotNull
    private Set<Long> categories;

    @NotNull
    private Set<Long> subCategories;

}
