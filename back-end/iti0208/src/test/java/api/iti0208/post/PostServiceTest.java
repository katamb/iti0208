package api.iti0208.post;

import api.iti0208.data.entity.Post;
import api.iti0208.repository.PostRepository;
import api.iti0208.repository.UserRepository;
import api.iti0208.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PostServiceTest {

    @TestConfiguration
    static class PostServiceTestContextConfiguration {
        @Bean
        public PostService postService() {
            return new PostService();
        }
    }

    @Autowired
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    private Long postId = 5L;

    @Before
    public void setUp() {
        Post post = new Post(postId, "testpost", "testpost", "Varia", 2L);
        postRepository.save(post);

        Mockito.when(postRepository.findById(postId))
                .thenReturn(Optional.of(post));
    }

    @Test
    public void findPostByID() {
        Optional<Post> found = postService.getOptionalPostItemById(postId);

        assertThat(found.get().getId())
                .isEqualTo(postId);
    }
}
