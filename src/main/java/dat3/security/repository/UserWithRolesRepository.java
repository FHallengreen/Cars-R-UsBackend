package dat3.security.repository;

import dat3.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
UserWithRolesRepository extends JpaRepository<UserWithRoles,String> {
    UserWithRoles findByUsername(String username);
}
