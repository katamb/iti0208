package api.iti0208.service;

import api.iti0208.entity.Post;
import api.iti0208.exception.PageNotFoundException;
import api.iti0208.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagingService {

    private final PostRepository postRepository;

    @Autowired
    public PagingService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private List<Post> getPosts(Page<Post> posts, int page) {
        if (page > posts.getTotalPages()) {
            throw new PageNotFoundException("This page does not exist!");
        }

        return posts.getContent();
    }

    public List<Post> getPosts(int page, int size) {
        Pageable pageableRequest = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAll(pageableRequest);

        return getPosts(posts, page);
    }

    public List<Post> getPosts(int page, int size, String topic) {
        Pageable pageableRequest = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAllByTopic(topic, pageableRequest);

        return getPosts(posts, page);
    }

    public Iterable<Post> findPosts(int page, int size, String searchTerm) {
        Pageable pageableRequest = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findBySearchTerm(searchTerm, pageableRequest);

        return getPosts(posts, page);
    }
}
