package hackeru.noameil.petfoodstore.service.User;

import hackeru.noameil.petfoodstore.dto.Item.ItemResponseDto;
import hackeru.noameil.petfoodstore.dto.User.UserRoleResponseDto;
import hackeru.noameil.petfoodstore.entity.Cart;
import hackeru.noameil.petfoodstore.entity.User;

public interface UserService {
    Cart getCurrentUserCart();
    User getCurrentUser();
    UserRoleResponseDto getCurrentUserDto();

}
