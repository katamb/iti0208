package api.iti0208.unit.service;

import api.iti0208.data.entity.Reply;
import api.iti0208.data.input.ReplyPatchInput;
import api.iti0208.exception.BadRequestException;
import api.iti0208.repository.PostRepository;
import api.iti0208.repository.ReplyRepository;
import api.iti0208.repository.UserRepository;
import api.iti0208.service.ReplyService;
import com.auth0.jwt.JWT;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
public class ReplyServiceTest {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ReplyRepository replyRepository;

    private ReplyService replyService;

    private String authToken;

    private long long_num = 5L;

    @Before
    public void setUp() {
        replyService = new ReplyService(replyRepository, userRepository);
        Mockito.when(replyRepository.save(any(Reply.class))).then(returnsFirstArg());

        // only needed to add the person who replied to the post
        String username = "testUser";
        authToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
    }

    @Test
    public void testSaveReply() {
        Reply reply = new Reply("test", 1L);
        Reply savedReply = replyService.save(reply, authToken);

        assertThat(savedReply.getReply()).isNotNull();
        assertEquals("testUser", savedReply.getPostedBy());
    }

    @Test
    public void testFindUsernameOfReplier() {
        Reply newReply = new Reply(long_num, "test", "testUser");
        Mockito.when(replyRepository.findById(any(Long.class))).thenReturn(Optional.of(newReply));

        String reply = replyService.findUsernameOfReplier(2L);
        assertEquals("testUser", reply);
    }

    @Test(expected = BadRequestException.class)
    public void testTryToFindUsernameOfReplier() {
        Mockito.when(replyRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        replyService.findUsernameOfReplier(1L);
    }

    @Test
    public void testPatchReply() {
        String newTitle = "newTitle";
        String username = "testUser";
        Reply newReply = new Reply(long_num, newTitle, username);
        Mockito.when(replyRepository.findById(any(Long.class))).thenReturn(Optional.of(newReply));

        Reply response = replyService.patch(
                new ReplyPatchInput(newTitle), 5L
        );
        assertEquals(newTitle, response.getReply());
    }

    @Test(expected = BadRequestException.class)
    public void testTryToPatchReply() {
        Mockito.when(replyRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        replyService.patch(new ReplyPatchInput("newTitle"), 5L);
    }
}
