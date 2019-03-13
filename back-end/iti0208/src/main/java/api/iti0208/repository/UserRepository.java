package api.iti0208.repository;


import api.iti0208.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface UserRepository extends JpaRepository<User, Long> {


    User findByEmail(String email);



}
