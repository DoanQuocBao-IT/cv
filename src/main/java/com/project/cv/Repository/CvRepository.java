package com.project.cv.Repository;

import com.project.cv.Model.Candidates;
import com.project.cv.Model.Cv;
import com.project.cv.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvRepository extends JpaRepository<Cv,Integer> {
    Cv findByCandidates(Candidates candidates);
}
