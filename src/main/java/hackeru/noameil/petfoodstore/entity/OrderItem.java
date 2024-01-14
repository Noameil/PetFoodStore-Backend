package hackeru.noameil.petfoodstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @NotNull
    private Long orderItemId;

    @NotNull
    private Long amount;


    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name ="cartId", referencedColumnName = "cartId")
    private Cart cart;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name ="orderId", referencedColumnName = "orderId")
    private Order order;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private Item item;

}
