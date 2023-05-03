package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.CreateCvDto;
import com.project.cv.Model.Candidates;
import com.project.cv.Model.Cv;
import com.project.cv.Model.Experience;
import com.project.cv.Model.User;
import com.project.cv.Repository.CandidateRepository;
import com.project.cv.Repository.CvRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvServiceImpl implements CvService {
    @Autowired
    CvRepository cvRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Override
    public Cv addCV(CreateCvDto createCvDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User candidate = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(candidate);
        Cv cv=new Cv();
        cv.setCandidates(candidates);
        if (createCvDto.getExperience().equals("less1year")) {
            cv.setExperience(Experience.lessoneyear.getExperience());
        } else if (createCvDto.getExperience().equals("1year")) {
            cv.setExperience(Experience.oneyear.getExperience());
        } else if (createCvDto.getExperience().equals("2year")) {
            cv.setExperience(Experience.twoyear.getExperience());
        } else if (createCvDto.getExperience().equals("3year")) {
            cv.setExperience(Experience.threeyear.getExperience());
        } else if (createCvDto.getExperience().equals("4year")) {
            cv.setExperience(Experience.fouryear.getExperience());
        } else if (createCvDto.getExperience().equals("5year")) {
            cv.setExperience(Experience.fiveyear.getExperience());
        } else if (createCvDto.getExperience().equals("over5year")) {
            cv.setExperience(Experience.overfiveyear.getExperience());
        } else{
            cv.setExperience(Experience.goingtowork.getExperience());
        }

        cv.setPosition(createCvDto.getPosition());
        cv.setProfession(createCvDto.getProfession());
        cv.setAddress(candidate.getAddress());
        return cvRepository.save(cv);
    }

    @Override
    public Cv updateCV(int id, CreateCvDto cvDto) {
        Cv cv = cvRepository.findById(id).get();
        cv.setPosition(cvDto.getPosition());
        if (cvDto.getExperience().equals("less1year")) {
            cv.setExperience(Experience.lessoneyear.getExperience());
        } else if (cvDto.getExperience().equals("1year")) {
            cv.setExperience(Experience.oneyear.getExperience());
        } else if (cvDto.getExperience().equals("2year")) {
            cv.setExperience(Experience.twoyear.getExperience());
        } else if (cvDto.getExperience().equals("3year")) {
            cv.setExperience(Experience.threeyear.getExperience());
        } else if (cvDto.getExperience().equals("4year")) {
            cv.setExperience(Experience.fouryear.getExperience());
        } else if (cvDto.getExperience().equals("5year")) {
            cv.setExperience(Experience.fiveyear.getExperience());
        } else if (cvDto.getExperience().equals("over5year")) {
            cv.setExperience(Experience.overfiveyear.getExperience());
        } else{
            cv.setExperience(Experience.goingtowork.getExperience());
        }
        cv.setProfession(cvDto.getProfession());
        cv.setAddress(cvDto.getAddress());
        return cvRepository.save(cv);
    }

    @Override
    public Cv allCV() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(user);
        return cvRepository.findByCandidates(candidates);
    }

}
