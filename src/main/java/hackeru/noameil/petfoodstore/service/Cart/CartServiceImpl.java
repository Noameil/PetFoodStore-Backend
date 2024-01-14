package hackeru.noameil.petfoodstore.service.Cart;

import hackeru.noameil.petfoodstore.dto.Cart.CartDTO;
import hackeru.noameil.petfoodstore.dto.Cart.CartResponseDto;
import hackeru.noameil.petfoodstore.dto.Cart.CartUpdateDTO;
import hackeru.noameil.petfoodstore.dto.Category.CategoryDeleteDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryRequestDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryResponseDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemResponseDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemMiniDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemResponseDto;
import hackeru.noameil.petfoodstore.dto.User.UserRoleResponseDto;
import hackeru.noameil.petfoodstore.entity.*;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import hackeru.noameil.petfoodstore.repository.*;
import hackeru.noameil.petfoodstore.service.User.UserService;
import hackeru.noameil.petfoodstore.service.User.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    private final UserService userService;

    @Override
    public CartDTO editCartItems(CartUpdateDTO dto) throws Exception {
        User user = userService.getCurrentUser();
        Cart cart = user.getCurrentCart();

        if(cart.getOrder() != null) {
            throw new Exception("This cart is already closed");
        }

        OrderItem orderItem = null;
        for(var orderItemInCart: cart.getCartItems()) {
            if(orderItemInCart.getItem().getItemId() == dto.getItemId()) {
                orderItem = orderItemInCart;
                break;
            }
        }

        if(orderItem == null) { // first time adding this item to the cart (this is 100% an addition)
            Item item = itemRepository.findById(dto.getItemId()).orElseThrow(() -> new ResourceNotFoundException("Item not found", dto.getItemId()));
            orderItem = OrderItem.builder().item(item).amount(dto.getAmount()).cart(cart).build();
            cart.getCartItems().add(orderItem);
        } else { // this can also be negative quantity
            orderItem.setAmount(orderItem.getAmount() + dto.getAmount());
            if (orderItem.getAmount() <= 0) {
                cart.getCartItems().remove(orderItem);
            }
        }
        userRepository.save(user);
        return modelMapper.map(cart, CartDTO.class);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
