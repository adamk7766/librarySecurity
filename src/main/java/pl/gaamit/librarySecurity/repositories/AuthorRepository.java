package pl.gaamit.librarySecurity.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.gaamit.librarySecurity.model.Author;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
