package hackeru.noameil.petfoodstore.controller;

import hackeru.noameil.petfoodstore.dto.User.UserRoleResponseDto;
import hackeru.noameil.petfoodstore.security.JWTProvider;
import hackeru.noameil.petfoodstore.service.User.UserDetailsServiceImpl;
import hackeru.noameil.petfoodstore.signin.SignInRequestDto;
import hackeru.noameil.petfoodstore.signup.SignUpRequestDto;
import hackeru.noameil.petfoodstore.signup.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserDetailsServiceImpl authService;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<UserRoleResponseDto> signUp(@RequestBody @Valid SignUpRequestDto dto) {
        System.out.println("signUp controller");
        return new ResponseEntity<>(authService.signUp(dto), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody @Valid SignInRequestDto dto){
        var user = authService.loadUserByUsername(dto.getUsername());
        var savedPassword = user.getPassword();
        var givenPassword = dto.getPassword();

        if (passwordEncoder.matches(givenPassword, savedPassword)) {
            //grant:
            var token = jwtProvider.generateToken(user.getUsername());

            return ResponseEntity.ok(Map.of("jwt", token));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}