package api.iti0208.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3)
    private String title;

    @NotNull
    private String topic;

    @NotNull
    @Size(min = 5)
    private String description;

    private String rewardDescription;

    private String fileLocation;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Reply> answers = new LinkedList<>();

    public Post(String title, String description, String topic) {
        this.title = title;
        this.description = description;
        this.topic = topic;
    }

}
