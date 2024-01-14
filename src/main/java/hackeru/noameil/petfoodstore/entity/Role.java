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
public class Role {
    @Id
    @GeneratedValue
    private Long roleId;

    @NotNull
    private String roleName;


    @ManyToMany(
            cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinTable(
       name = "user_roles",
       joinColumns = {@JoinColumn(name="roleId")},
       inverseJoinColumns = {@JoinColumn(name="userId")}
    )
    private Set<User> users;
}


