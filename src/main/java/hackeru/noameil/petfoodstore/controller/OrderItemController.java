package hackeru.noameil.petfoodstore.controller;

import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemRequestDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemResponseDto;
import hackeru.noameil.petfoodstore.service.OrderItem.OrderItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderitems")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemResponseDto> createOrderItem(
            @RequestBody @Valid OrderItemRequestDto dto,
            UriComponentsBuilder uriBuilder){

        //service -> save(dto)
        var saved = orderItemService.createOrderItem(dto);


        //uri version b:
        var uri = uriBuilder.path("/api/v1/orderitems/{id}")
                .buildAndExpand(saved.getItem().getItemId())
                .toUri();

        //response.created
        return ResponseEntity.created(uri).body(saved);

    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponseDto>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> getOrderItemById(@Valid @NotNull @PathVariable long id) {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> updateOrderItemById(
            @Valid @NotNull @PathVariable long id,
            @Valid @RequestBody OrderItemRequestDto dto) {
        return ResponseEntity.ok(orderItemService.updateOrderItemById(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> deleteOrderItemById(
            @Valid @NotNull @PathVariable long id) {
        return ResponseEntity.ok(orderItemService.deleteOrderItemById(id));
    }

}
