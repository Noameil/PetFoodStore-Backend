package hackeru.noameil.petfoodstore.controller;

import hackeru.noameil.petfoodstore.dto.User.UserRoleResponseDto;
import hackeru.noameil.petfoodstore.service.User.UserDetailsServiceImpl;
import hackeru.noameil.petfoodstore.service.User.UserService;
import hackeru.noameil.petfoodstore.signin.SignInRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<UserRoleResponseDto> getUser() {
        logger.info("getUser Called");
        return ResponseEntity.ok(userService.getCurrentUserDto());
    }

}
