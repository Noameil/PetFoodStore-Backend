package hackeru.noameil.petfoodstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hackeru.noameil.petfoodstore.validators.Item.UniqueItemName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @NotNull
    @GeneratedValue
    private Long itemId;

    @NotNull
    private String itemName;

    @NotNull
    private Double cost;

    private String imgURL;


    @ManyToMany
    @JoinTable(
            name = "item_categories",
            joinColumns = @JoinColumn(name = "itemId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId")
    )
    private Set<Category> categories = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "item_subCategories",
            joinColumns = @JoinColumn(name = "itemId"),
            inverseJoinColumns = @JoinColumn(name = "subCategoryId")
    )
    private Set<SubCategory> subCategories = new HashSet<>();

}
