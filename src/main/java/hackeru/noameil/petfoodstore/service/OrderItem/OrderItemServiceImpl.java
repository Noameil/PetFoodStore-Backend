package hackeru.noameil.petfoodstore.service.OrderItem;

import hackeru.noameil.petfoodstore.dto.Category.CategoryResponseDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemRequestDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemResponseDto;
import hackeru.noameil.petfoodstore.entity.Category;
import hackeru.noameil.petfoodstore.entity.Item;
import hackeru.noameil.petfoodstore.entity.Order;
import hackeru.noameil.petfoodstore.entity.OrderItem;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import hackeru.noameil.petfoodstore.repository.OrderItemRepository;
import hackeru.noameil.petfoodstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final ModelMapper modelMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItemResponseDto createOrderItem(OrderItemRequestDto orderItemRequestDto) {
        var entity = modelMapper.map(orderItemRequestDto, OrderItem.class);

        var saved = orderItemRepository.save(entity);

        return modelMapper.map(saved, OrderItemResponseDto.class);
    }

    @Override
    public List<OrderItemResponseDto> getAllOrderItems() {
        return orderItemRepository
                .findAll()
                .stream()
                .map(c -> modelMapper.map(c, OrderItemResponseDto.class))
                .toList();
    }

    @Override
    public OrderItemResponseDto getOrderItemById(Long id) {
        var orderItem = getOrderItemEntity(id);
        OrderItemResponseDto dto =
                modelMapper.map(orderItem, OrderItemResponseDto.class);

        return dto;
    }

    @Override
    public OrderItemResponseDto updateOrderItemById(OrderItemRequestDto dto, long id) {
        var orderItemFromDb = getOrderItemEntity(id);

        orderItemFromDb.setOrderItemId(dto.getAmount());
        var saved = orderItemRepository.save(orderItemFromDb);

        return modelMapper.map(saved, OrderItemResponseDto.class);
    }

    @Override
    public OrderItemResponseDto deleteOrderItemById(Long id) {
        OrderItem orderItem = getOrderItemEntity(id);
        orderItemRepository.deleteById(id);

        return modelMapper.map(orderItem, OrderItemResponseDto.class);
    }

    private OrderItem getOrderItemEntity(Long id) {
        OrderItem orderItem =
                orderItemRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("OrderItem", id)
                );
        return orderItem;
    }
}
