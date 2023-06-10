package com.project.cv.Repository;

import com.project.cv.Model.Messages;
import com.project.cv.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Messages,Integer> {
    List<Messages> findBySenderAndReceiverOrSenderAndReceiverOrderByCreateAtDesc(User sender, User receiver, User receiver1, User sender1);
    //List<Messages> findBySenderOrReceiverOrderByCreateAtAsc(User sender,User receiver);
    @Query("SELECT m FROM Messages m WHERE (m.sender = :user OR m.receiver = :user) AND m.createAt IN (SELECT MAX(m2.createAt) FROM Messages m2 WHERE m2.sender = :user OR m2.receiver = :user GROUP BY CASE WHEN m2.sender = :user THEN m2.receiver ELSE m2.sender END) ORDER BY m.createAt DESC")
    List<Messages> findBySenderOrReceiverOrderByCreateAtAsc(@Param("user") User user);
}
