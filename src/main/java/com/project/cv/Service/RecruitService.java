package com.project.cv.Service;

import com.project.cv.Dto.RecruitDetailDto;
import com.project.cv.Dto.RecruitDto;
import com.project.cv.Model.Recruit;

import java.util.List;

public interface RecruitService {
    Recruit addRecruit(RecruitDto recruitDto);
    Recruit updateRecruit(int id, RecruitDto recruitDto);
    List<Recruit> findAllRecruitForCompany();
    List<RecruitDetailDto> findAllRecruit();
    Recruit findRecruitById(int recruit_id);
}
