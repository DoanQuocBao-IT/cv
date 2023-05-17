package com.project.cv.Repository;

import com.project.cv.Model.Candidates;
import com.project.cv.Model.Company;
import com.project.cv.Model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Integer> {
    @Query("SELECT f.company FROM Follow f WHERE f.candidates = :candidates")
    List<Company> getAllCompanyByCandidateFollow(Candidates candidates);
    Follow findByCompanyAndCandidates(Company company,Candidates candidates);
    boolean existsByCompanyAndCandidates(Company company,Candidates candidates);
}
