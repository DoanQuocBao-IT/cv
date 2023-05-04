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
}
