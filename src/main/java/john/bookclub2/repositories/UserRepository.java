package john.bookclub2.repositories;

import org.springframework.data.repository.CrudRepository;
import john.bookclub2.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}