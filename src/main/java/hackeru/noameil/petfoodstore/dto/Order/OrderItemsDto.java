package hackeru.noameil.petfoodstore.dto.Order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemsDto {
    private long itemId;
    private long amount;
}
