package api.iti0208.post;

import api.iti0208.data.entity.Post;
import api.iti0208.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testToSeePosts() throws Exception {
        // given
        Post post = new Post("testpost", "testpost", "Varia",
                2L, "user_test");
        entityManager.persist(post);

        // when
        Post found = postRepository.findById(post.getId()).get();

        // then
        assertThat(found.getTitle()).isEqualTo(post.getTitle());
    }

}