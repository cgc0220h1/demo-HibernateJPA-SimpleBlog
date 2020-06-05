package repository;

import model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
}
