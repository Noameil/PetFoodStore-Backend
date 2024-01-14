package hackeru.noameil.petfoodstore.entity;

import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long userId;

    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orders;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE} ,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "userId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "roleId"
            )
    )
    private Set<Role> roles;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_carts",
            joinColumns = @JoinColumn(
                    name = "userId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cartId"
            )
    )
    private Set<Cart> carts;


    public Cart getCurrentCart() {
        return carts.stream()
                .filter(c -> c.getOrder() == null)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User has no has", 0));
    }
}
