package com.project.cv.Service;

import com.project.cv.Model.Company;
import com.project.cv.Model.Recruit;

import java.util.List;

public interface SavejobService {
    List<Recruit> findAllJobSave();
    void followJob(int recruit_id);
    void deleteFollowJob(int recruit_id);
    boolean isFollowedJob(int recruit_id);
}
