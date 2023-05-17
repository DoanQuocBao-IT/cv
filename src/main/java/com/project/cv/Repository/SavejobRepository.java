package com.project.cv.Repository;

import com.project.cv.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavejobRepository extends JpaRepository<Savejob,Integer> {
    @Query("SELECT s.recruit FROM Savejob s WHERE s.candidates = :candidates")
    List<Recruit> getAllJobByCandidateSave(Candidates candidates);
    Savejob findByRecruitAndCandidates(Recruit recruit, Candidates candidates);
    boolean existsByRecruitAndCandidates(Recruit recruit, Candidates candidates);

}
