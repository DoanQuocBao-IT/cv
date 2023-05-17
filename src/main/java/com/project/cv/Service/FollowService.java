package com.project.cv.Service;

import com.project.cv.Model.Company;

import java.util.List;

public interface FollowService {
    List<Company> findAllCompanyFollow();
    void followCompany(int company_id);
    void deleteFollow(int company_id);
    boolean isFollowedCompany(int company_id);
}
