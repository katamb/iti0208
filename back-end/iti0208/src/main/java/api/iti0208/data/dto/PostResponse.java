package api.iti0208.data.dto;

import api.iti0208.data.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<Post> posts;
    private int amountOfPages;
}
