package repository;

import model.Author;
import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByAuthor_Name(String name);

    Page<Post> findPostByCreateTimeBetween(Timestamp createTime, Timestamp createTime2, Pageable pageable);
}
