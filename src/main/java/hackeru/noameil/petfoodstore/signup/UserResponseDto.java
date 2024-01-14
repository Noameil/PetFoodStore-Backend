package hackeru.noameil.petfoodstore.signup;

import hackeru.noameil.petfoodstore.entity.Cart;
import hackeru.noameil.petfoodstore.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

//User without a password:
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private long userId;
    private String username;
    private String email;
    private Set<Role> roles;
    private Set<Cart> carts;
}
