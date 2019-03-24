package api.iti0208.post;

import api.iti0208.data.entity.Post;
import api.iti0208.repository.PostRepository;
import api.iti0208.repository.UserRepository;
import api.iti0208.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    private PostService postService;

    private Long postId = 5L;

    @Before
    public void setUp() {
        postService = new PostService(postRepository, userRepository);
    }

    @Test
    public void testSavePost() {
        Post post = new Post("test", "test", "Varia");
        when(postRepository.save(any(Post.class))).then(returnsFirstArg());
        Post savedPost = postService.save(post);

        assertThat(savedPost.getTitle()).isNotNull();
    }

    /*@Test
    public void findPostByID() {
        Optional<Post> found = postService.getOptionalPostItemById(postId);

        assertThat(found.get().getId())
                .isEqualTo(postId);
    }*/

}
