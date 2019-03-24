package api.iti0208.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private long userId;

    private Date postedAt;

    private String postedBy;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "postId", cascade = CascadeType.ALL)
    private List<Reply> answers = new LinkedList<>();

    @PrePersist
    protected void onCreate() {
        postedAt = new Date();
    }

    public Post(String title, String description, String topic) {
        this.title = title;
        this.description = description;
        this.topic = topic;
    }

    public Post(Long id, String title, String description, String topic) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topic = topic;
    }

    public Post(Long id, String title, String description, String topic, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.userId = userId;
    }

    public Post(String title, String description, String topic, Long userId, String postedBy) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.userId = userId;
        this.postedBy = postedBy;
    }

    public Post(String title, String description, String topic, Long userId) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.userId = userId;
    }

    public Post(String topic, String title, String description, String rewardDescription, Long userId) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.rewardDescription = rewardDescription;
        this.userId = userId;
    }

    public Post(String topic, String title, String description, String rewardDescription,
                String fileLocation, Long userId) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.rewardDescription = rewardDescription;
        this.fileLocation = fileLocation;
        this.userId = userId;
    }

}
