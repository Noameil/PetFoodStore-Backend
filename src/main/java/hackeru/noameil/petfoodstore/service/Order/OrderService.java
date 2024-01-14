package hackeru.noameil.petfoodstore.service.Order;

import hackeru.noameil.petfoodstore.dto.Order.OrderRequestDto;
import hackeru.noameil.petfoodstore.dto.Order.OrderResponseDto;
import hackeru.noameil.petfoodstore.error.NoSuchOrderException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
}
