package hackeru.noameil.petfoodstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hackeru.noameil.petfoodstore.validators.Category.UniqueCategoryName;
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
public class Category {

    @Id
    @GeneratedValue
    @NotNull
    private Long categoryId;

    @NotNull
//    @UniqueCategoryName
    private String categoryName;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Item> items = new HashSet<>();

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<SubCategory> subCategories;

}
