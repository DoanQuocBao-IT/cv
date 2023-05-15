package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.CreateCvDto;
import com.project.cv.Model.*;
import com.project.cv.Repository.AddressRepository;
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
    @Autowired
    AddressRepository addressRepository;
    @Override
    public Cv addCV(CreateCvDto createCvDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User candidate = userRepository.findUserByName(authentication.getName());
        Candidates candidates=candidateRepository.findByCandidate(candidate);
        Cv cv=new Cv();
        cv.setCandidates(candidates);
        if (createCvDto.getExperience()!=null){
            if (createCvDto.getExperience().equals("lessoneyear")) {
                cv.setExperience(Experience.lessoneyear.getExperience());
            } else if (createCvDto.getExperience().equals("oneyear")) {
                cv.setExperience(Experience.oneyear.getExperience());
            } else if (createCvDto.getExperience().equals("twoyear")) {
                cv.setExperience(Experience.twoyear.getExperience());
            } else if (createCvDto.getExperience().equals("threeyear")) {
                cv.setExperience(Experience.threeyear.getExperience());
            } else if (createCvDto.getExperience().equals("fouryear")) {
                cv.setExperience(Experience.fouryear.getExperience());
            } else if (createCvDto.getExperience().equals("fiveyear")) {
                cv.setExperience(Experience.fiveyear.getExperience());
            } else if (createCvDto.getExperience().equals("overfiveyear")) {
                cv.setExperience(Experience.overfiveyear.getExperience());
            } else{
                cv.setExperience(Experience.goingtowork.getExperience());
            }
        }else
            cv.setExperience("No setup");

        if (createCvDto.getGoals()!=null)
            cv.setGoals(createCvDto.getGoals());
        if (createCvDto.getStudy()!=null)
            cv.setStudy(createCvDto.getStudy());
        if (createCvDto.getWork()!=null)
            cv.setWork(createCvDto.getWork());
        if (createCvDto.getSkill()!=null)
            cv.setSkill(createCvDto.getSkill());
        if (createCvDto.getPrize()!=null)
            cv.setPrize(createCvDto.getPrize());
        if (createCvDto.getCertificate()!=null)
            cv.setCertificate(createCvDto.getCertificate());
        if (createCvDto.getPosition()!=null)
            cv.setPosition(createCvDto.getPosition());
        if (createCvDto.getProfession()!=null)
            cv.setProfession(createCvDto.getProfession());
        if (createCvDto.getAddress()!=null) {
            int address_id = Integer.parseInt(createCvDto.getAddress());
            Address address = addressRepository.findById(address_id).get();
            cv.setAddress(address);
        }else {
            Address address = addressRepository.findById(22).get();
            cv.setAddress(address);
        }
        return cvRepository.save(cv);
    }

    @Override
    public Cv updateCV(int id, CreateCvDto cvDto) {
        Cv cv = cvRepository.findById(id).get();

        if (cvDto.getExperience()!=null){
            if (cvDto.getExperience().equals("lessoneyear")) {
                cv.setExperience(Experience.lessoneyear.getExperience());
            } else if (cvDto.getExperience().equals("oneyear")) {
                cv.setExperience(Experience.oneyear.getExperience());
            } else if (cvDto.getExperience().equals("twoyear")) {
                cv.setExperience(Experience.twoyear.getExperience());
            } else if (cvDto.getExperience().equals("threeyear")) {
                cv.setExperience(Experience.threeyear.getExperience());
            } else if (cvDto.getExperience().equals("fouryear")) {
                cv.setExperience(Experience.fouryear.getExperience());
            } else if (cvDto.getExperience().equals("fiveyear")) {
                cv.setExperience(Experience.fiveyear.getExperience());
            } else if (cvDto.getExperience().equals("overfiveyear")) {
                cv.setExperience(Experience.overfiveyear.getExperience());
            } else{
                cv.setExperience(Experience.goingtowork.getExperience());
            }
        }else
            cv.setExperience(cv.getExperience());

        if (cvDto.getGoals()!=null)
            cv.setGoals(cvDto.getGoals());
        if (cvDto.getStudy()!=null)
            cv.setStudy(cvDto.getStudy());
        if (cvDto.getWork()!=null)
            cv.setWork(cvDto.getWork());
        if (cvDto.getSkill()!=null)
            cv.setSkill(cvDto.getSkill());
        if (cvDto.getPrize()!=null)
            cv.setPrize(cvDto.getPrize());
        if (cvDto.getCertificate()!=null)
            cv.setCertificate(cvDto.getCertificate());
        if (cvDto.getPosition()!=null)
            cv.setPosition(cvDto.getPosition());
        if (cvDto.getProfession()!=null)
            cv.setProfession(cvDto.getProfession());
        if (cvDto.getAddress()!=null) {
            int address_id = Integer.parseInt(cvDto.getAddress());
            Address address = addressRepository.findById(address_id).get();
            cv.setAddress(address);
        }else {
            cv.setAddress(cv.getAddress());
        }
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
