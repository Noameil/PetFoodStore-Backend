package hackeru.noameil.petfoodstore.service.User;

import hackeru.noameil.petfoodstore.dto.User.UserRoleResponseDto;
import hackeru.noameil.petfoodstore.entity.Cart;
import hackeru.noameil.petfoodstore.entity.OrderItem;
import hackeru.noameil.petfoodstore.entity.User;
import hackeru.noameil.petfoodstore.error.BadRequestException;
import hackeru.noameil.petfoodstore.error.PetFoodStoreException;
import hackeru.noameil.petfoodstore.repository.CartRepository;
import hackeru.noameil.petfoodstore.repository.RoleRepository;
import hackeru.noameil.petfoodstore.repository.UserRepository;
import hackeru.noameil.petfoodstore.signup.SignUpRequestDto;
import hackeru.noameil.petfoodstore.signup.UserResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    //props:
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final CartRepository cartRepository;
    @Transactional
    public UserRoleResponseDto signUp(SignUpRequestDto dto) {
        //1) get the user role from role repository:
        val userRole = roleRepository.findByRoleNameIgnoreCase("ROLE_USER")
                .orElseThrow(() -> new PetFoodStoreException("Please contact admin"));
        //2) if email/username exists -> Go Sign in (Exception)
        val byUser = userRepository.findByUsernameIgnoreCase(dto.getUsername().trim());
        val byEmail = userRepository.findByEmailIgnoreCase(dto.getEmail().trim());

        if (byEmail.isPresent()) {
            throw new BadRequestException("email", "Email already exists");
        } else if (byUser.isPresent()) {
            throw new BadRequestException("username", "Username already exists");
        }

        //3) val user = new User(... encoded-password )
        var user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword().trim()))
                .roles(Set.of(userRole))
                .orders(new ArrayList<>())
                .carts(new HashSet<>())
                .build();
    try {
        var cart = Cart.builder().user(user).cartItems(new HashSet<>()).build();
        user.getCarts().add(cart);
        var savedUser = userRepository.save(user);
        System.out.println("signUp service");
        return modelMapper.map(savedUser, UserRoleResponseDto.class);

    }catch(Exception e) {
        e.printStackTrace();
        return null;
    }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //fetch our user entity from our database
        var user = userRepository
                .findByUsernameIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));

        var roles = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).toList();

        //return new org.springframework.security.core.userdetails.User
        //spring User implements UserDetails
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
