package api.iti0208;

import api.iti0208.entity.Post;
import api.iti0208.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    public void run(String... args) throws Exception {
        Stream.of(Arrays.asList("Developer", "Android developer."), Arrays.asList("Designer", "Web designer."), Arrays.asList("Spider", "Web designer."))
                .forEach(title -> repo.save(new Post(title.get(0), title.get(1))));

        repo.findAll().forEach(System.out::println);
    }
}
