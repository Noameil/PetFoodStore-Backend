package hackeru.noameil.petfoodstore.dto.OrderItem;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequestDto {

    @NotNull
    private Long amount;

    @NotNull
    private Double itemCost;

    @NotNull
    private Long order;

}
