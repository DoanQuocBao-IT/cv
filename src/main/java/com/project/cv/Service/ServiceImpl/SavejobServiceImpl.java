package com.project.cv.Service.ServiceImpl;

import com.project.cv.Model.Candidates;
import com.project.cv.Model.Recruit;
import com.project.cv.Model.Savejob;
import com.project.cv.Model.User;
import com.project.cv.Repository.*;
import com.project.cv.Service.SavejobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavejobServiceImpl implements SavejobService {
    @Autowired
    SavejobRepository savejobRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    RecruitRepository recruitRepository;
    @Override
    public List<Recruit> findAllJobSave() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        return savejobRepository.getAllJobByCandidateSave(candidates);
    }

    @Override
    public void followJob(int recruit_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        Recruit recruit=recruitRepository.findById(recruit_id).get();
        Savejob savejob=new Savejob();
        savejob.setCandidates(candidates);
        savejob.setRecruit(recruit);
        savejobRepository.save(savejob);
    }

    @Override
    public void deleteFollowJob(int recruit_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        Recruit recruit=recruitRepository.findById(recruit_id).get();
        Savejob savejob=savejobRepository.findByRecruitAndCandidates(recruit,candidates);
        savejobRepository.delete(savejob);
    }

    @Override
    public boolean isFollowedJob(int recruit_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        Recruit recruit=recruitRepository.findById(recruit_id).get();
        return savejobRepository.existsByRecruitAndCandidates(recruit,candidates);
    }
}
