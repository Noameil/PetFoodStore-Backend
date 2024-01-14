package hackeru.noameil.petfoodstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cart {

    @Id
    @NotNull
    @GeneratedValue
    private Long cartId;

    @NotNull
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<OrderItem> cartItems;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private User user;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "cartId", nullable = false)
    private Order order;

//    private Boolean isClosed;

}
