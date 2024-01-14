package hackeru.noameil.petfoodstore.dto.Cart;

import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
    private Long cartId;
//    private Boolean isClosed;
    private Set<OrderItemResponseDto> cartItems;
}
