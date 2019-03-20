package api.iti0208.controller;

import api.iti0208.data.entity.Post;
import api.iti0208.data.dto.PostResponse;
import api.iti0208.data.entity.Reply;
import api.iti0208.repository.PostRepository;
import api.iti0208.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Map;

import static api.iti0208.security.SecurityConstants.*;

// PS! PostResponse holds the response and the amount of pages!

@RestController
public class PostController {

    private final PostService postService;
    private final PostRepository postRepo;

    @Autowired
    public PostController(PostService postService, PostRepository postRepo) {

        this.postService = postService;
        this.postRepo = postRepo;
    }

    @GetMapping("api/posts")
    public PostResponse getPosts(@RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "15") int size,
                                 @RequestParam(value = "topic", defaultValue = "all") String topic) {
        return postService.getPosts(page, size, topic);
    }

    @GetMapping("api/posts/{id}")
    public Post getPostItemById(@PathVariable Long id) {
        return postService.getPostItemById(id);
    }

    @GetMapping("api/posts/find")
    public PostResponse findPosts(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "15") int size,
                                  @RequestParam(value = "searchTerm", defaultValue = "") String searchTerm) {
        return postService.findPosts(page, size, searchTerm);
    }

    @PostMapping("api/add/post")
    public Post save(@RequestBody @Valid Post item,
                     @RequestHeader(value = HEADER_STRING) String header) {
        return postService.savePost(item, header);
    }

    @DeleteMapping("api/delete/post/{id}")
    public void delete(@PathVariable Long id) {
        postRepo.deleteById(id);
    }

    @PostMapping("api/edit/post/{id}")
    public Post save(@RequestBody Object obj, @PathVariable Long id) {
        System.out.println("Editing post");
        if (postRepo.findById(id).isPresent()) {
            Post editedPost = postRepo.findById(id).get();
            String newTitle = (String)((Map)obj).get("title");
            String newDescription = (String)((Map)obj).get("description");
            String newRewardDescription = (String)((Map)obj).get("rewardDescription");
            System.out.println(newDescription);
            editedPost.setTitle(newTitle);
            editedPost.setDescription(newDescription);
            editedPost.setRewardDescription(newRewardDescription);
            return postRepo.save(editedPost);
        }
        return null;

    }

}
