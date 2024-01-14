package hackeru.noameil.petfoodstore.dto.Cart;

import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemMiniDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemResponseDto;
import hackeru.noameil.petfoodstore.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;



@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class CartResponseDto {

    private long cartId;
    private Set<OrderItem> cartItems;
}
