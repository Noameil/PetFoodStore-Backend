package hackeru.noameil.petfoodstore.controller;

import hackeru.noameil.petfoodstore.dto.Cart.CartDTO;
import hackeru.noameil.petfoodstore.dto.Cart.CartUpdateDTO;
import hackeru.noameil.petfoodstore.dto.Item.ItemDeleteDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemRequestDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemResponseDto;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import hackeru.noameil.petfoodstore.service.Cart.CartService;
import hackeru.noameil.petfoodstore.service.Item.ItemService;
import hackeru.noameil.petfoodstore.service.User.UserDetailsServiceImpl;
import hackeru.noameil.petfoodstore.service.User.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PutMapping
    public ResponseEntity<CartDTO> editCartItems(
            @Valid @RequestBody CartUpdateDTO dto) throws Exception {

        return ResponseEntity.ok(cartService.editCartItems(dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartById(
            @Valid @NotNull @PathVariable long id) {
        cartService.deleteCart(id);
        return ResponseEntity.ok().build();
    }

}

