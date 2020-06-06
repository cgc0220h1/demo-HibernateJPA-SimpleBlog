package service.author;

import model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImp implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImp(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findByPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<Author> findByPage(int startPageIndex, int numberOfElements) {
        return null;
    }

    @Override
    public Page<Author> findPage(int startPageIndex, int numberOfElements) {
        return null;
    }

    @Override
    public Author findOne(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.orElse(null);
    }

    @Override
    public Author save(Author model) {
        return authorRepository.save(model);
    }

    @Override
    public void delete(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
