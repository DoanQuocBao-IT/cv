package com.project.cv.Service;

import com.project.cv.Model.Apply;
import com.project.cv.Model.Cv;

import java.util.List;

public interface ApplyService {
    Apply applyCvToRecruit(int recruit_id);
    int countApplyCv(int recruit_id);
    List<Cv> allCvApplyByRecruitId(int recruit_id);
}