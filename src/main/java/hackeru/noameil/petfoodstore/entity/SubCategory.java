package hackeru.noameil.petfoodstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SubCategory {

    @Id
    @GeneratedValue
    @NotNull
    private Long subCategoryId;

    @NotNull
    private String subCategoryName;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;


    @JsonIgnore
    @ManyToMany(mappedBy = "subCategories" )
    private Set<Item> items = new HashSet<>();

}
