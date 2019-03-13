package api.iti0208.service;

import api.iti0208.data.entity.Post;
import api.iti0208.data.dto.PostResponse;
import api.iti0208.exception.PageNotFoundException;
import api.iti0208.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static api.iti0208.service.UserService.getUsernameFromJwtToken;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostItemById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new PageNotFoundException("Sorry, this page does not exist!");
        }
    }

    private PostResponse getPosts(Page<Post> posts, int page) {
        if (page > posts.getTotalPages()) {
            throw new PageNotFoundException("This page does not exist!");
        }

        return  new PostResponse(posts.getContent(), posts.getTotalPages());
    }

    public PostResponse getPosts(int page, int size, String topic) {
        Pageable pageableRequest = PageRequest.of(page, size);

        Page<Post> posts;
        if (topic.equals("all")) {
            posts = postRepository.findAll(pageableRequest);
        } else {
            posts = postRepository.findAllByTopic(topic, pageableRequest);
        }

        return getPosts(posts, page);
    }

    public PostResponse findPosts(int page, int size, String searchTerm) {
        Pageable pageableRequest = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findBySearchTerm(searchTerm, pageableRequest);

        return getPosts(posts, page);
    }

    public Post savePost(Post item, String header) {
        String username = null;
        if (header != null) {
            username = getUsernameFromJwtToken(header);
        }
        if (username != null) {
            item.setPostedBy(username);
        }
        return postRepository.save(item);
    }


}
