package hackeru.noameil.petfoodstore.service.User;

import hackeru.noameil.petfoodstore.dto.Cart.CartResponseDto;
import hackeru.noameil.petfoodstore.dto.User.RoleResponseDto;
import hackeru.noameil.petfoodstore.dto.User.UserRoleResponseDto;
import hackeru.noameil.petfoodstore.entity.Cart;
import hackeru.noameil.petfoodstore.entity.User;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import hackeru.noameil.petfoodstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



    @Override
    public Cart getCurrentUserCart() {
        User user = getCurrentUser();
        return user.getCurrentCart();
    }

    @Override
    public User getCurrentUser() {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) auth.getPrincipal();
        return userRepository.findByUsernameIgnoreCase(userName).orElseThrow(() -> new ResourceNotFoundException("User not found with username " + userName, 0));
    }
    @Override
    public UserRoleResponseDto getCurrentUserDto() {
        var user = getCurrentUser();
        Cart cart = user.getCurrentCart();
        return UserRoleResponseDto.builder().
                    roles(user.getRoles().stream().map(role -> RoleResponseDto.builder().roleId(role.getRoleId()).roleName(role.getRoleName()).build()).collect(Collectors.toSet()))
                    .userId(user.getUserId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .cart(CartResponseDto.builder().cartId(cart.getCartId()).cartItems(cart.getCartItems()).build()).build();
    }


}
