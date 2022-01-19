package nl.robbertij.musicnmatch.repository;

import nl.robbertij.musicnmatch.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
