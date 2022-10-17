package john.bookclub2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import john.bookclub2.models.Book;
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {}