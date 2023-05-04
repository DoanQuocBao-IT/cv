package com.project.cv.Repository;

import com.project.cv.Model.Apply;
import com.project.cv.Model.Candidates;
import com.project.cv.Model.Cv;
import com.project.cv.Model.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply,Integer> {
    @Query("SELECT COUNT(a) FROM Apply a WHERE a.recruit.id = :recruit_id")
    int countApplyByRecruitId(@Param("recruit_id") int recruit_id);
    @Query("SELECT a.cv FROM Apply a WHERE a.recruit.id = :recruit_id")
    List<Cv> allCVApplyByRecruitId(@Param("recruit_id") int recruit_id);
    @Query("SELECT a.recruit FROM Apply a WHERE a.cv.candidates = :candidates")
    List<Recruit> findRecruitsByCvCandidates(Candidates candidates);

}
