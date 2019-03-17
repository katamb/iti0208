package api.iti0208.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.print.attribute.HashAttributeSet;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String firstName;

    private String lastName;

    //@Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> grantedAuthorities;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Post> userPosts = new HashSet<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Reply> userReplies = new HashSet<>();

    /*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;*/


    public AppUser(String username, String password, List<GrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }
}
