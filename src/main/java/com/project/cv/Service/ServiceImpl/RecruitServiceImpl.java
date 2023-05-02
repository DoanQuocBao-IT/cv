package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.RecruitDto;
import com.project.cv.Model.Experience;
import com.project.cv.Model.Gender;
import com.project.cv.Model.Recruit;
import com.project.cv.Model.User;
import com.project.cv.Repository.RecruitRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitServiceImpl implements RecruitService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecruitRepository recruitRepository;
    @Override
    public Recruit addRecruit(RecruitDto recruitDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User company = userRepository.findUserByName(authentication.getName());
        Recruit recruit=new Recruit();
        recruit.setUser(company);
        recruit.setProfession(recruitDto.getProfession());
        recruit.setSalary(recruitDto.getSalary());
        recruit.setPosition(recruitDto.getPosition());
        recruit.setInventory(recruitDto.getInventory());
        if (recruitDto.getGender().equals("male")) {
            recruit.setGender(Gender.male.getGender());
        } else if (recruitDto.getGender().equals("female")) {
            recruit.setGender(Gender.female.getGender());
        } else if (recruitDto.getGender().equals("other")) {
            recruit.setGender(Gender.other.getGender());
        }else {
            recruit.setGender(Gender.norequire.getGender());
        }

        if (recruitDto.getExperience().equals("not")){
            recruit.setExperience(Experience.goingtowork.getExperience());
        } else if (recruitDto.getExperience().equals("less1year")) {
            recruit.setExperience(Experience.lessoneyear.getExperience());
        } else if (recruitDto.getExperience().equals("1year")) {
            recruit.setExperience(Experience.oneyear.getExperience());
        } else if (recruitDto.getExperience().equals("2year")) {
            recruit.setExperience(Experience.twoyear.getExperience());
        } else if (recruitDto.getExperience().equals("3year")) {
            recruit.setExperience(Experience.threeyear.getExperience());
        } else if (recruitDto.getExperience().equals("4year")) {
            recruit.setExperience(Experience.fouryear.getExperience());
        } else if (recruitDto.getExperience().equals("5year")) {
            recruit.setExperience(Experience.fiveyear.getExperience());
        }else{
            recruit.setExperience(Experience.norequire.getExperience());
        }

        recruit.setResponsibilities(recruitDto.getResponsibilities());
        recruit.setQualifications(recruitDto.getQualifications());
        recruit.setInterests(recruitDto.getInterests());
        recruit.setAddress(recruitDto.getAddress());
        return recruitRepository.save(recruit);
    }

    @Override
    public Recruit updateRecruit(int id, RecruitDto recruitDto) {
        Recruit recruit= recruitRepository.findById(id).get();
        recruit.setProfession(recruitDto.getProfession());
        recruit.setSalary(recruitDto.getSalary());
        recruit.setPosition(recruitDto.getPosition());
        recruit.setInventory(recruitDto.getInventory());
        if (recruitDto.getGender().equals("male")) {
            recruit.setGender(Gender.male.getGender());
        } else if (recruitDto.getGender().equals("female")) {
            recruit.setGender(Gender.female.getGender());
        } else if (recruitDto.getGender().equals("other")) {
            recruit.setGender(Gender.other.getGender());
        }else {
            recruit.setGender(Gender.norequire.getGender());
        }

        if (recruitDto.getExperience().equals("not")){
            recruit.setExperience(Experience.goingtowork.getExperience());
        } else if (recruitDto.getExperience().equals("less1year")) {
            recruit.setExperience(Experience.lessoneyear.getExperience());
        } else if (recruitDto.getExperience().equals("1year")) {
            recruit.setExperience(Experience.oneyear.getExperience());
        } else if (recruitDto.getExperience().equals("2year")) {
            recruit.setExperience(Experience.twoyear.getExperience());
        } else if (recruitDto.getExperience().equals("3year")) {
            recruit.setExperience(Experience.threeyear.getExperience());
        } else if (recruitDto.getExperience().equals("4year")) {
            recruit.setExperience(Experience.fouryear.getExperience());
        } else if (recruitDto.getExperience().equals("5year")) {
            recruit.setExperience(Experience.fiveyear.getExperience());
        }else{
            recruit.setExperience(Experience.norequire.getExperience());
        }

        recruit.setResponsibilities(recruitDto.getResponsibilities());
        recruit.setQualifications(recruitDto.getQualifications());
        recruit.setInterests(recruitDto.getInterests());
        recruit.setAddress(recruitDto.getAddress());
        return recruitRepository.save(recruit);
    }

    @Override
    public List<Recruit> findAllRecruitForCompany() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        return recruitRepository.findByUser(user);
    }

    @Override
    public List<Recruit> findAllRecruit() {
        return recruitRepository.findAll();
    }
}
