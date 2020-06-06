package service.tag;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.TagRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImp implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImp(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Category> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<Category> findByPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<Category> findByPage(int startPageIndex, int numberOfElements) {
        return null;
    }

    @Override
    public Page<Category> findPage(int startPageIndex, int numberOfElements) {
        return null;
    }

    @Override
    public Category findOne(Long id) {
        Optional<Category> tag = tagRepository.findById(id);
        return tag.orElse(null);
    }

    @Override
    public Category save(Category category) {
        return tagRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        tagRepository.delete(category);
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
