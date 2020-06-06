package service;

import model.Post;

public interface AdvancedGenericService<T> extends GenericService<T> {
    T findByOtherPost(Post post);
}
