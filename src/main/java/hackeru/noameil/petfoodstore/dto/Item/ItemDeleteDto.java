package hackeru.noameil.petfoodstore.dto.Item;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDeleteDto {
    private Long itemId;
    private String itemName;
}
