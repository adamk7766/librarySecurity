package pl.gaamit.librarySecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.gaamit.librarySecurity.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
