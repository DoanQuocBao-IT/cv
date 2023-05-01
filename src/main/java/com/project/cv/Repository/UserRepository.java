package com.project.cv.Repository;

import com.project.cv.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT u FROM User u WHERE u.username=:userName")
    Optional<User> findUserByUsername(String userName);
    @Query(value = "SELECT u FROM User u WHERE u.username=:userName")
    User findUserByName(String userName);
}
