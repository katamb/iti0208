package api.iti0208.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    //todo: upload files

    public Post(String title) {
        this.title = title;
    }

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
