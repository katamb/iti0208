package api.iti0208.service;

import api.iti0208.data.entity.Post;
import api.iti0208.data.output.PostResponse;
import api.iti0208.data.input.PostPatchInput;
import api.iti0208.exception.BadRequestException;
import api.iti0208.exception.PageNotFoundException;
import api.iti0208.repository.PostRepository;
import api.iti0208.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static api.iti0208.service.UserService.getUsernameFromJwtToken;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    /*@Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }*/

    /**
     * Get methods to receive posts.
     */

    public Optional<Post> getOptionalPostItemById(Long id) {
        return postRepository.findById(id);
    }

    public Post getPostItemById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new PageNotFoundException("Sorry, this page does not exist!");
        }
    }

    public PostResponse getPosts(int page, int size, String topic, String order, String sortBy) {
        Pageable pageableRequest = getPageable(page, size, order, sortBy);

        Page<Post> posts;
        if (topic.equals("all")) {
            posts = postRepository.findAll(pageableRequest);
        } else {
            posts = postRepository.findAllByTopic(topic, pageableRequest);
        }

        return getPostsResponse(posts, page);
    }

    public PostResponse findPosts(int page, int size, String searchTerm, String order, String sortBy) {
        Pageable pageableRequest = getPageable(page, size, order, sortBy);
        Page<Post> posts = postRepository.findBySearchTerm(searchTerm, pageableRequest);

        return getPostsResponse(posts, page);
    }

    private PostResponse getPostsResponse(Page<Post> posts, int page) {
        if (page > posts.getTotalPages()) {
            throw new PageNotFoundException("This page does not exist!");
        }

        return new PostResponse(posts.getContent(), posts.getTotalPages());
    }

    private Pageable getPageable(int page, int size, String order, String sortBy) {
        Pageable pageableRequest = null;

        if (order.equals("ascending")) {
            pageableRequest = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        } else {
            pageableRequest = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }

        return pageableRequest;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    /**
     * Save methods to save posts to DB.
     */

    public Post savePost(Post item, String header) {
        String username = null;

        if (header != null) {
            username = getUsernameFromJwtToken(header);
        }
        if (username != null) {
            item.setPostedBy(username);
            item.setUserId(userRepository.findIdByUsername(username));
        }

        return postRepository.save(item);
    }

    public Post save(Post item) {
        return postRepository.save(item);
    }

    /**
     * Patch methods to change posts in DB.
     */

    public Post patchPost(PostPatchInput obj, Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            String newTitle = obj.getTitle();
            String newDescription = obj.getDescription();

            if (newTitle != null && newTitle.length() != 0) {
                postRepository.updateTitle(id, newTitle);
            }
            if (newDescription != null && newDescription.length() != 0) {
                postRepository.updateDescription(id, newDescription);
            }

            return getPostItemById(id);
        }

        throw new BadRequestException("Problem updating Your post!");
    }

    /**
     * Delete methods to remove posts from DB.
     */

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    /**
     * Methods for authorization.
     */

    public String findUsernameOfPoster(Long id) {
        return getPostItemById(id).getPostedBy();
    }
}
