package hackeru.noameil.petfoodstore.service.Cart;

import hackeru.noameil.petfoodstore.dto.Cart.CartDTO;
import hackeru.noameil.petfoodstore.dto.Cart.CartResponseDto;
import hackeru.noameil.petfoodstore.dto.Cart.CartUpdateDTO;
import hackeru.noameil.petfoodstore.dto.Category.CategoryResponseDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemResponseDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemMiniDto;
import hackeru.noameil.petfoodstore.dto.OrderItem.OrderItemResponseDto;
import hackeru.noameil.petfoodstore.dto.User.UserRoleResponseDto;

public interface CartService {


    CartDTO editCartItems(CartUpdateDTO dto) throws Exception;
    void deleteCart(Long id);

}
