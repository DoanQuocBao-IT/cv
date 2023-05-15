package com.project.cv.Service;

import com.project.cv.Dto.RecruitDetailDto;
import com.project.cv.Model.Apply;
import com.project.cv.Model.Cv;
import com.project.cv.Model.Recruit;

import java.util.List;

public interface ApplyService {
    Apply applyCvToRecruit(int recruit_id);
    int countApplyCv(int recruit_id);
    List<Cv> allCvApplyByRecruitId(int recruit_id);
    List<RecruitDetailDto> allRecruitApply();
    void approvedCvApplyRecruit(int recruit_id, int cv_id);
    int countCvPassed(int cv_id);
    List<Cv> allCvApplyByRecruitIdApproved(int recruit_id);
    List<Cv> allCvApplyByRecruitIdPending(int recruit_id);
    List<RecruitDetailDto> allRecruitApplyApproved(int cv_id);

}
