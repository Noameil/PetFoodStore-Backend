package hackeru.noameil.petfoodstore.dto.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateDTO {
    private long itemId;
    private long amount;
}
