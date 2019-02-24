package api.iti0208;

import api.iti0208.entity.Post;
import api.iti0208.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.stream.Stream;

@Component
public class SampleData implements CommandLineRunner {

    private final PostRepository repo;

    @Autowired
    public SampleData(PostRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Stream.of(Arrays.asList("Developer", "Android developer.", "Mathematics"),
                Arrays.asList("Designer", "Web designer.", "Biology"),
                Arrays.asList("Designer", "Web designer.", "Physics"),
                Arrays.asList("Designer", "Web designer.", "Computer Science"),
                Arrays.asList("Designer", "Web designer.", "Physics"),
                Arrays.asList("Designer", "Web designer.", "Biology"),
                Arrays.asList("Designer", "Web designer.", "Mathematics"),
                Arrays.asList("Designer", "Web designer.", "Computer Science"),
                Arrays.asList("Designer", "Web designer.", "Biology"),
                Arrays.asList("Designer", "Web designer.", "Biology"),
                Arrays.asList("Designer", "Web designer.", "Computer Science"),
                Arrays.asList("Designer", "Web designer.", "Mathematics"),
                Arrays.asList("Designer", "Web designer.", "Biology"),
                Arrays.asList("Designer", "Web designer.", "Physics"),
                Arrays.asList("Designer", "Web designer.", "Physics"),
                Arrays.asList("Designer", "Web designer.", "Computer Science"),
                Arrays.asList("Designer", "Web designer.", "Computer Science"),
                Arrays.asList("Designer", "Web designer.", "Computer Science"),
                Arrays.asList("Designer", "Web designer.", "Mathematics"),
                Arrays.asList("Designer", "Web designer.", "Biology"),
                Arrays.asList("Designer", "Web designer.", "Physics"),
                Arrays.asList("Designer", "Web designer.", "Physics"),
                Arrays.asList("Designer", "Web designer.", "Biology"),
                Arrays.asList("Designer", "Web designer.", "Physics"),
                Arrays.asList("Designer", "Web designer.", "Physics"),
                Arrays.asList("Designer", "Web designer.", "Computer Science"),
                Arrays.asList("Designer", "Web designer.", "Computer Science"),
                Arrays.asList("Designer", "Web designer.", "Mathematics"),
                Arrays.asList("Designer", "Web designer.", "Mathematics"),
                Arrays.asList("Designer", "Web designer.", "Biology"),
                Arrays.asList("Designer", "Web designer.", "Mathematics"),
                Arrays.asList("Designer", "Web designer.", "Physics"),
                Arrays.asList("Designer", "Web designer.", "Physics"),
                Arrays.asList("Designer", "Web designer.", "Mathematics"),
                Arrays.asList("Spider", "Web designer.", "Mathematics"))
                .forEach(title -> repo.save(new Post(title.get(0), title.get(1), title.get(2))));

        repo.findAll().forEach(System.out::println);
    }
}
