package pl.gaamit.librarySecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.gaamit.librarySecurity.model.Role;



public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);

}
