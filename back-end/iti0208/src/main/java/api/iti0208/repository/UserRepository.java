package api.iti0208.repository;

import api.iti0208.data.entity.AppUser;

import api.iti0208.data.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);

    AppUser findByUsername(String username);

    @Query("select a.id from AppUser a where a.username =:username")
    Long findIdByUsername(@Param("username") String username);

    @Query("select a.username from AppUser a where a.id =:id")
    String findUsernameById(@Param("id") Long id);
}
