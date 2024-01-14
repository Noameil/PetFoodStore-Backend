package hackeru.noameil.petfoodstore.dto.User;

import hackeru.noameil.petfoodstore.dto.Cart.CartResponseDto;
import hackeru.noameil.petfoodstore.entity.Cart;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponseDto {
    private long userId;
    private String username;
    private String email;
    private Set<RoleResponseDto> roles;
    private CartResponseDto cart;
}