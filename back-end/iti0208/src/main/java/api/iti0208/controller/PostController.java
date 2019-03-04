package api.iti0208.controller;

import api.iti0208.entity.Post;
import api.iti0208.entity.PostResponse;
import api.iti0208.exception.PageNotFoundException;
import api.iti0208.repository.PostRepository;
import api.iti0208.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

// PS! PostResponse holds the response and the amount of pages!

@RestController
public class PostController {

    private final PostRepository dao;
    private final PagingService pagingService;

    @Autowired
    public PostController(PostRepository dao, PagingService pagingService) {
        this.dao = dao;
        this.pagingService = pagingService;
    }

    @GetMapping("api/posts")
    public PostResponse getPosts(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "15") int size,
                                   @RequestParam(value = "topic", defaultValue = "all") String topic) {
        if (topic.equals("all")) {
            return pagingService.getPosts(page, size);
        } else {
            return pagingService.getPosts(page, size, topic);
        }
    }

    @GetMapping("api/posts/{id}")
    public Post getPostItemById(@PathVariable Long id) {
        Optional<Post> post = dao.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new PageNotFoundException("Sorry, this page does not exist!");
        }
    }

    @GetMapping("api/posts/find")
    public PostResponse findPosts(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "15") int size,
                                  @RequestParam(value = "searchTerm", defaultValue = "") String searchTerm) {
        return pagingService.findPosts(page, size, searchTerm);
    }

    @PostMapping("api/add/post")
    public Post save(@RequestBody @Valid Post item) {
        return dao.save(item);
    }

}
