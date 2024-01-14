package hackeru.noameil.petfoodstore.signup;

import hackeru.noameil.petfoodstore.entity.Cart;
import hackeru.noameil.petfoodstore.entity.Role;
import hackeru.noameil.petfoodstore.validators.Email.UniqueEmail;
import hackeru.noameil.petfoodstore.validators.Username.UniqueUsername;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20, message = "username must contain at least 2 letters")
    @UniqueUsername
    private String username;

    @NotNull
    @Email
    @NotEmpty
    @UniqueEmail
    private String email;

    @NotEmpty
    @Size(min = 8, max = 20)
    @NotNull(message = "password is mandatory")
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?\\W).{8,20}$",
            message = "password must contain at least 8 characters, one or more lower case letters, uppercase letter, symbol, digits"
    )
    private String password;

    private Set<Role> roles;

    private Set<Cart> carts;
}
