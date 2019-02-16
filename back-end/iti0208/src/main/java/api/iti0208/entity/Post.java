package api.iti0208.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String rewardDescription;
    private String fileLocation;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Reply> answers = new ArrayList<>();

    public Post(String title) {
        this.title = title;
    }

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
