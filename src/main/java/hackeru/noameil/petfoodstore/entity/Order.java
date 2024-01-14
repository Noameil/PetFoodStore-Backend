package hackeru.noameil.petfoodstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private Cart cart;

    private String shippingAddress;

}
