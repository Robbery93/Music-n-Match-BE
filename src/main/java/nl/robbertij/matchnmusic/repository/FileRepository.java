package nl.robbertij.matchnmusic.repository;

import nl.robbertij.matchnmusic.model.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long> {
}
