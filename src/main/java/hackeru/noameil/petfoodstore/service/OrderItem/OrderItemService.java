package hackeru.noameil.petfoodstore.service.OrderItem;

import hackeru.noameil.petfoodstore.dto.Item.ItemRequestDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemResponseDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemRequestDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemResponseDto;
import hackeru.noameil.petfoodstore.entity.OrderItem;
import jakarta.validation.Valid;

import java.util.List;

public interface OrderItemService {
//    List<OrderItem> getCustomQueryItems();

    OrderItemResponseDto createOrderItem(OrderItemRequestDto orderItemRequestDto);

    List<OrderItemResponseDto> getAllOrderItems();

    OrderItemResponseDto getOrderItemById(Long id);

    OrderItemResponseDto updateOrderItemById(@Valid OrderItemRequestDto dto, long id);

    OrderItemResponseDto deleteOrderItemById(Long id);
}
