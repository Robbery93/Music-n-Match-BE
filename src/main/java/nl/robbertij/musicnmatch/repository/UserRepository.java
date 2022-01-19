package nl.robbertij.musicnmatch.repository;

import nl.robbertij.musicnmatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
