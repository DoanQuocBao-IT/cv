package com.project.cv.Service.ServiceImpl;

import com.project.cv.Model.Candidates;
import com.project.cv.Model.Company;
import com.project.cv.Model.Follow;
import com.project.cv.Model.User;
import com.project.cv.Repository.CandidateRepository;
import com.project.cv.Repository.CompanyRepository;
import com.project.cv.Repository.FollowRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    FollowRepository followRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Override
    public List<Company> findAllCompanyFollow() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        return followRepository.getAllCompanyByCandidateFollow(candidates);
    }

    @Override
    public void followCompany(int company_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        Company company=companyRepository.findById(company_id).get();
        Follow follow=new Follow();
        follow.setCandidates(candidates);
        follow.setCompany(company);
        followRepository.save(follow);
    }

    @Override
    public void deleteFollow(int company_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        Company company=companyRepository.findById(company_id).get();
        Follow follow=followRepository.findByCompanyAndCandidates(company,candidates);
        followRepository.delete(follow);
    }

    @Override
    public boolean isFollowedCompany(int company_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        Company company=companyRepository.findById(company_id).get();
        return followRepository.existsByCompanyAndCandidates(company,candidates);
    }
}
