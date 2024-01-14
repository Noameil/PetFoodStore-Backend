package hackeru.noameil.petfoodstore.controller;

import hackeru.noameil.petfoodstore.dto.Order.OrderRequestDto;
import hackeru.noameil.petfoodstore.dto.Order.OrderResponseDto;
import hackeru.noameil.petfoodstore.error.NoSuchOrderException;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import hackeru.noameil.petfoodstore.service.Order.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody @Valid OrderRequestDto dto,
            UriComponentsBuilder uriBuilder){

            var saved = orderService.createOrder(dto);
            var uri = uriBuilder.path("/api/v1/orders/{id}")
                    .buildAndExpand(saved.getOrderId())
                    .toUri();
            return ResponseEntity.created(uri).body(saved);
    }
}
