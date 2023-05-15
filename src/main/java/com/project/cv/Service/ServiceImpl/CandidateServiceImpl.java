package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.CandidateDto;
import com.project.cv.Model.Candidates;
import com.project.cv.Model.Gender;
import com.project.cv.Model.User;
import com.project.cv.Repository.CandidateRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Candidates getProfileCandidate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        return candidateRepository.findByCandidate(user);
    }

    @Override
    public Candidates getCandidateById(int candidate_id) {
        return candidateRepository.findById(candidate_id).get();
    }

    @Override
    public Candidates updateCandidate(CandidateDto candidateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        if (candidateDto.getBirthday()!=null){
            candidates.setBirthday(candidateDto.getBirthday());
        }else {
            candidates.setBirthday(candidates.getBirthday());
        }
        if (candidateDto.getGender()!=null){
            if (candidateDto.getGender().equals("male")) {
                candidates.setGender(Gender.male.getGender());
            } else if (candidateDto.getGender().equals("female")) {
                candidates.setGender(Gender.female.getGender());
            } else {
                candidates.setGender(Gender.other.getGender());
            }
        }else
            candidates.setGender(candidates.getGender());


        if (candidateDto.getIntroduce()!=null)
            candidates.setIntroduce(candidateDto.getIntroduce());
        if (candidateDto.getHobby()!=null)
            candidates.setHobby(candidateDto.getHobby());
        if (candidateDto.getCertificate()!=null)
            candidates.setCertificate(candidateDto.getCertificate());
        return candidateRepository.save(candidates);
    }
}
