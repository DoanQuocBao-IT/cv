package com.project.cv.Repository;

import com.project.cv.Model.Candidates;
import com.project.cv.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidates,Integer> {
    Candidates findByCandidate(User candidate);
}
