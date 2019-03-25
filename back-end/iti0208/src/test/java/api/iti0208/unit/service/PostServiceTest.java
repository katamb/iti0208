package api.iti0208.unit.service;

import api.iti0208.data.entity.AppUser;
import api.iti0208.data.entity.Post;
import api.iti0208.data.input.PostPatchInput;
import api.iti0208.data.output.PostResponse;
import api.iti0208.exception.BadRequestException;
import api.iti0208.exception.PageNotFoundException;
import api.iti0208.repository.PostRepository;
import api.iti0208.repository.ReplyRepository;
import api.iti0208.repository.UserRepository;
import api.iti0208.service.PostService;
import api.iti0208.service.ReplyService;
import com.auth0.jwt.JWT;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static api.iti0208.security.SecurityConstants.EXPIRATION_TIME;
import static api.iti0208.security.SecurityConstants.SECRET;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ReplyRepository replyRepository;

    private PostService postService;

    private String authToken;

    @Before
    public void setUp() {
        postService = new PostService(postRepository, userRepository);
        Mockito.when(postRepository.save(any(Post.class))).then(returnsFirstArg());

        // only needed to add the person who posted to the post
        String username = "testUser";
        authToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
    }

    @Test
    public void testSavePost() {
        Post post = new Post("test", "test", "Varia");
        Post savedPost = postService.savePost(post, authToken);

        assertThat(savedPost.getTitle()).isNotNull();
        assertEquals("testUser", savedPost.getPostedBy());
    }

    @Test(expected = PageNotFoundException.class)
    public void testTryToFindPostByID() {
        postService.getPostItemById(1L);
    }

    @Test
    public void testFindPostByID() {
        Mockito.when(postRepository.findById(1L)).thenReturn(
                Optional.of(new Post("test", "test", "Varia"))
        );

        Post post = postService.getPostItemById(1L);
        assertEquals("test", post.getTitle());
    }

    @Test
    public void testFindPostByTopic() {
        int page = 0;
        int size = 10;
        String topic = "Varia";
        String order = "ascending";
        String sortBy = "title";

        Mockito.when(postRepository.findAllByTopic(any(String.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(
                        new Post("abcde", "test", topic),
                        new Post("bcdef", "test", topic),
                        new Post("cdefg", "test", topic))
                ));
        PostResponse response = postService.getPosts(page, size, topic, order, sortBy);

        assertEquals(1, response.getAmountOfPages());
        assertEquals("abcde", response.getPosts().get(0).getTitle());
    }

    @Test
    public void testFindAllPosts() {
        int page = 0;
        int size = 10;
        String topic = "all";
        String order = "ascending";
        String sortBy = "title";

        Mockito.when(postRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(
                        new Post("abcde", "test", "Varia"),
                        new Post("bcdef", "test", "Physics"),
                        new Post("cdefg", "test", "Mathematics"))
                ));
        PostResponse response = postService.getPosts(page, size, topic, order, sortBy);

        assertEquals(1, response.getAmountOfPages());
        assertEquals("abcde", response.getPosts().get(0).getTitle());
    }

    @Test
    public void testFindThroughSearch() {
        int page = 0;
        int size = 10;
        String searchTerm = "cde";
        String order = "ascending";
        String sortBy = "title";

        Mockito.when(postRepository.findBySearchTerm(any(String.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(
                        new Post("abcde", "test", "Varia"),
                        new Post("bcdef", "test", "Physics"),
                        new Post("cdefg", "test", "Mathematics"))
                ));
        PostResponse response = postService.findPosts(page, size, searchTerm, order, sortBy);

        assertEquals(1, response.getAmountOfPages());
        assertEquals("abcde", response.getPosts().get(0).getTitle());
    }

    @Test(expected = BadRequestException.class)
    public void testTryToPatchPost() {
        postService.patchPost(new PostPatchInput("123456", "123456"), 1L);
    }

    @Test
    public void testPatchPost() {
        String newTitle = "newTitle";
        String oldTitle = "oldTitle";
        String newDescription = "newDescripion";
        String oldDescription = "oldDescription";

        Mockito.when(postRepository.findById(1L))
                .thenReturn(
                        Optional.of(new Post(newTitle, oldDescription, "Varia"))
                );
        Post response = postService.patchPost(
                new PostPatchInput(newTitle, oldDescription), 1L
        );
        assertEquals(oldDescription, response.getDescription());
        assertEquals(newTitle, response.getTitle());

        Mockito.when(postRepository.findById(2L))
                .thenReturn(
                        Optional.of(new Post(oldTitle, newDescription, "Varia"))
                );
        response = postService.patchPost(
                new PostPatchInput(oldTitle, newDescription), 2L
        );
        assertEquals(newDescription, response.getDescription());
        assertEquals(oldTitle, response.getTitle());
    }
}
