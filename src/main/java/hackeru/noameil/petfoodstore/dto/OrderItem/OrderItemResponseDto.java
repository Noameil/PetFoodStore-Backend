package hackeru.noameil.petfoodstore.dto.OrderItem;

import hackeru.noameil.petfoodstore.dto.Item.ItemResponseDto;
import hackeru.noameil.petfoodstore.dto.Order.OrderResponseDto;
import hackeru.noameil.petfoodstore.entity.Order;
import hackeru.noameil.petfoodstore.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDto {
    private Long amount;
    private ItemResponseDto item;
}
