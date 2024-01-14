package hackeru.noameil.petfoodstore.dto.Order;

import hackeru.noameil.petfoodstore.dto.Cart.CartDTO;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemResponseDto;
import hackeru.noameil.petfoodstore.entity.OrderItem;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private Long orderId;
    private Double total;
    private CartDTO cart;
    private String shippingAddress;
}
