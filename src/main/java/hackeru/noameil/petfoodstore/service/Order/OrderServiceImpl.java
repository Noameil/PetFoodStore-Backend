package hackeru.noameil.petfoodstore.service.Order;

import hackeru.noameil.petfoodstore.dto.Cart.CartDTO;
import hackeru.noameil.petfoodstore.dto.Order.OrderItemsDto;
import hackeru.noameil.petfoodstore.dto.Order.OrderRequestDto;
import hackeru.noameil.petfoodstore.dto.Order.OrderResponseDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemResponseDto;
import hackeru.noameil.petfoodstore.entity.*;
import hackeru.noameil.petfoodstore.error.NoSuchOrderException;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import hackeru.noameil.petfoodstore.repository.*;
import hackeru.noameil.petfoodstore.service.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        User currentUser = userService.getCurrentUser();
        Cart cart = currentUser.getCurrentCart();
        var newOrder = Order.builder()
                .cart(cart)
                .shippingAddress(orderRequestDto.getShippingAddress())
                .user(currentUser)
                .build();
        currentUser.getOrders().add(newOrder);
        cart.setOrder(newOrder);
        userRepository.save(currentUser);
        double total = 0.0f;
        for (OrderItem orderItem : cart.getCartItems())
            total += orderItem.getItem().getCost() * orderItem.getAmount();

        return OrderResponseDto.builder()
                .cart(modelMapper.map(cart, CartDTO.class))
                .shippingAddress(orderRequestDto.getShippingAddress())
                .orderId(newOrder.getOrderId())
                .total(total)
                .build();
    }

}