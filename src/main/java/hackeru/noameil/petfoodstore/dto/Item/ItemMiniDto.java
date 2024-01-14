package hackeru.noameil.petfoodstore.dto.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemMiniDto {
    private Long itemId;
    private String itemName;
}
