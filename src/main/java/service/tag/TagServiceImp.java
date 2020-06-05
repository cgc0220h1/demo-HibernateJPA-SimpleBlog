package service.tag;

import model.Tag;
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
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> findByPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<Tag> findByPage(int startPageIndex, int numberOfElements) {
        return null;
    }

    @Override
    public Page<Tag> findPage(int startPageIndex, int numberOfElements) {
        return null;
    }

    @Override
    public Tag findOne(Long id) {
        Optional<Tag> tag = tagRepository.findById(id);
        return tag.orElse(null);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
