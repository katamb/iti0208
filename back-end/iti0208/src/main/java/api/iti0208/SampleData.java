package api.iti0208;

import api.iti0208.data.entity.AppUser;
import api.iti0208.data.entity.Post;
import api.iti0208.repository.PostRepository;
import api.iti0208.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

@Component
public class SampleData implements CommandLineRunner {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SampleData(PostRepository postRepository, UserRepository userRepository,
                      BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        userRepository.save(
                new AppUser("admin_test",
                        bCryptPasswordEncoder.encode("admin_test"),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                )
        );

        userRepository.save(
                new AppUser("user_test",
                        bCryptPasswordEncoder.encode("user_test"),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                )
        );

        Stream.of(
                Arrays.asList("Mathematics", "Mathematics 1", "Mathematics"),
                Arrays.asList("Biology", "Biology  1 ", "Biology"),
                Arrays.asList("Physics", "Phyysika 1", "Physics"),
                Arrays.asList("Computer_Science", "Computer_Science 0", "Computer_Science"),
                Arrays.asList("Physics", "Phyysika 2", "Physics"),
                Arrays.asList("Biology", "Biology  2 ", "Biology"),
                Arrays.asList("Computer_Science", "Computer_Science 1", "Computer_Science"),
                Arrays.asList("Biology", "Biology  3 ", "Biology"),
                Arrays.asList("Biology", "Biology  4 ", "Biology"),
                Arrays.asList("Computer_Science", "Computer_Science 2", "Computer_Science"),
                Arrays.asList("Mathematics", "Mathematics 2", "Mathematics"),
                Arrays.asList("Biology", "Biology  5 ", "Biology"),
                Arrays.asList("Physics", "Phyysika 3", "Physics"),
                Arrays.asList("Physics", "Phyysika 4", "Physics"),
                Arrays.asList("Computer_Science", "Computer_Science 3", "Computer_Science"),
                Arrays.asList("Computer_Science", "Computer_Science 4", "Computer_Science"),
                Arrays.asList("Computer_Science", "Computer_Science 5", "Computer_Science"),
                Arrays.asList("Mathematics", "Mathematics 3", "Mathematics"),
                Arrays.asList("Biology", "Biology  6 ", "Biology"),
                Arrays.asList("Physics", "Phyysika 5", "Physics"),
                Arrays.asList("Physics", "Phyysika 6", "Physics"),
                Arrays.asList("Biology", "Biology  7 ", "Biology"),
                Arrays.asList("Physics", "Phyysika 7", "Physics"),
                Arrays.asList("Physics", "Phyysika 8", "Physics"),
                Arrays.asList("Computer_Science", "Computer_Science 6", "Computer_Science"),
                Arrays.asList("Computer_Science", "Computer_Science 7", "Computer_Science"),
                Arrays.asList("Mathematics", "Mathematics 3", "Mathematics"),
                Arrays.asList("Mathematics", "Mathematics 4", "Mathematics"),
                Arrays.asList("Biology", "Biology  8 ", "Biology"),
                Arrays.asList("Mathematics", "Mathematics 5", "Mathematics"),
                Arrays.asList("Physics", "Phyysika 9", "Physics"),
                Arrays.asList("Physics", "Phyysika 10", "Physics"),
                Arrays.asList("Mathematics", "Mathematics 6", "Mathematics"),
                Arrays.asList("Mathematics", "Mathematics 7", "Mathematics"))
                .forEach(title -> postRepository.save(
                        new Post(title.get(0), title.get(1), title.get(2), 2L)
                ));

        postRepository.findAll().forEach(System.out::println);


    }
}
