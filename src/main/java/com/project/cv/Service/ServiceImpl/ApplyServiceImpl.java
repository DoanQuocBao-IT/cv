package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.RecruitDetailDto;
import com.project.cv.Model.*;
import com.project.cv.Repository.*;
import com.project.cv.Service.ApplyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    RecruitRepository recruitRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    CvRepository cvRepository;
    @Autowired
    private ApplyRepository applyRepository;

    @Override
    public Apply applyCvToRecruit(int recruit_id) {
        Recruit recruit=recruitRepository.findById(recruit_id).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        Cv cv=cvRepository.findByCandidates(candidates);
        Apply apply=new Apply();
        apply.setCv(cv);
        apply.setRecruit(recruit);
        apply.setApproved(false);
        return applyRepository.save(apply);
    }

    @Override
    public int countApplyCv(int recruit_id) {
        return applyRepository.countApplyByRecruitId(recruit_id);
    }

    @Override
    public List<Cv> allCvApplyByRecruitId(int recruit_id) {
        return applyRepository.allCVApplyByRecruitId(recruit_id);
    }

    @Override
    public List<RecruitDetailDto> allRecruitApply() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        List<Recruit> recruits=applyRepository.findRecruitsByCvCandidates(candidates);
        ModelMapper modelMapper=new ModelMapper();
        return recruits.stream().map(recruit -> modelMapper.map(recruit, RecruitDetailDto.class)).collect(Collectors.toList());
    }

    @Override
    public void approvedCvApplyRecruit(int recruit_id, int cv_id) {
        Recruit recruit=recruitRepository.findById(recruit_id).get();
        Cv cv=cvRepository.findById(cv_id).get();
        Apply apply=applyRepository.findByRecruitAndCv(recruit,cv);
        apply.setApproved(true);
        applyRepository.save(apply);
    }

    @Override
    public int countCvPassed(int cv_id) {
        return 0;
    }

    @Override
    public List<Cv> allCvApplyByRecruitIdApproved(int recruit_id) {
        return applyRepository.findByApprovedTrue(recruit_id);
    }

    @Override
    public List<Cv> allCvApplyByRecruitIdPending(int recruit_id) {
        return applyRepository.findByApprovedFalse(recruit_id);
    }

    @Override
    public List<RecruitDetailDto> allRecruitApplyApproved(int cv_id) {
        List<Recruit> recruits=applyRepository.findJobByApprovedTrue(cv_id);
        ModelMapper modelMapper=new ModelMapper();
        return recruits.stream().map(recruit -> modelMapper.map(recruit, RecruitDetailDto.class)).collect(Collectors.toList());

    }
}
