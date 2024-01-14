package hackeru.noameil.petfoodstore.repository;

import hackeru.noameil.petfoodstore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleNameIgnoreCase(String name);
}
