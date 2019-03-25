package api.iti0208.unit.repository;

import api.iti0208.data.entity.Post;
import api.iti0208.data.entity.Reply;
import api.iti0208.repository.ReplyRepository;
import api.iti0208.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ReplyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testToSeeReplies() {
        Reply post = new Reply(2L, "testReply", "userTest");
        entityManager.persist(post);

        Reply found = replyRepository.findById(post.getId()).get();

        assertThat(found.getReply()).isEqualTo(post.getReply());
    }

}