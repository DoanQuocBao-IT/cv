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
    @Query("SELECT a.cv FROM Apply a WHERE a.recruit.id = :recruit_id AND a.approved = true")
    List<Cv> findByApprovedTrue(@Param("recruit_id") int recruit_id);
    @Query("SELECT a.cv FROM Apply a WHERE a.recruit.id = :recruit_id AND a.approved = false")
    List<Cv> findByApprovedFalse(@Param("recruit_id") int recruit_id);
    @Query("SELECT a.recruit FROM Apply a WHERE a.cv.id = :cv_id AND a.approved = true")
    List<Recruit> findJobByApprovedTrue(@Param("cv_id") int cv_id);
    @Query("SELECT COUNT(a) FROM Apply a WHERE a.recruit.id = :recruit_id")
    int countCvPass(@Param("recruit_id") int recruit_id);

    Apply findByRecruitAndCv(Recruit recruit,Cv cv);

}
