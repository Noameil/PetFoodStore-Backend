package hackeru.noameil.petfoodstore.dto.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemMiniDto {

    private Long orderItemId;
    private Long amount;

}
