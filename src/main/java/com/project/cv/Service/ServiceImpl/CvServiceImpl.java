package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.CreateCvDto;
import com.project.cv.Model.Cv;
import com.project.cv.Model.User;
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
    @Override
    public Cv addCV(CreateCvDto createCvDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User candidate = userRepository.findUserByName(authentication.getName());
        Cv cv=new Cv();
        cv.setUser(candidate);
        cv.setExperience(createCvDto.getExperience());
        cv.setPosition(createCvDto.getPosition());
        cv.setProfession(createCvDto.getProfession());
        cv.setAddress(candidate.getAddress());
        return cvRepository.save(cv);
    }

    @Override
    public Cv updateCV(int id, CreateCvDto cvDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User candidate = userRepository.findUserByName(authentication.getName());
        Cv cv = cvRepository.findById(id).get();
        cv.setPosition(cvDto.getPosition());
        cv.setExperience(cvDto.getExperience());
        cv.setProfession(cvDto.getProfession());
        cv.setAddress(cvDto.getAddress());
        return cvRepository.save(cv);
    }

    @Override
    public Cv allCV() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        return cvRepository.findByUser(user);
    }

    @Override
    public List<Cv> findAllCv() {
        return cvRepository.findAll();
    }
}
