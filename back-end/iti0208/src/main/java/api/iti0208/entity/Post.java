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

    private String header;

    private String description;

    public Post(String header) {
        this.header = header;
    }

    public Post(String header, String description) {
        this.header = header;
        this.description = description;
    }
}
