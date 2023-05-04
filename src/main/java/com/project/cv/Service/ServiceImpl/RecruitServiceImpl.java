package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.RecruitDetailDto;
import com.project.cv.Dto.RecruitDto;
import com.project.cv.Model.*;
import com.project.cv.Repository.AddressRepository;
import com.project.cv.Repository.CompanyRepository;
import com.project.cv.Repository.RecruitRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.RecruitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruitServiceImpl implements RecruitService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecruitRepository recruitRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;
    @Override
    public Recruit addRecruit(RecruitDto recruitDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User company = userRepository.findUserByName(authentication.getName());
        Company companies=companyRepository.findByCompany(company);
        companies.setInventory_job(companies.getInventory_job()+1);
        companyRepository.save(companies);
        Recruit recruit=new Recruit();
        recruit.setCompany(companies);
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
        recruit.setFromDate(new Date());
        recruit.setToDate(recruitDto.getToDate());

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
        recruit.setToDate(recruitDto.getToDate());
        return recruitRepository.save(recruit);
    }

    @Override
    public List<Recruit> findAllRecruitForCompany() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Company company=companyRepository.findByCompany(user);
        return recruitRepository.findByCompany(company);
    }

    @Override
    public List<RecruitDetailDto> findAllRecruit() {
        List<Recruit> recruits=recruitRepository.findAll();
        ModelMapper modelMapper=new ModelMapper();
        return recruits.stream().map(recruit -> modelMapper.map(recruit, RecruitDetailDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RecruitDetailDto> findAllRecruitBySearch(String search) {
        List<Recruit> recruits=recruitRepository.findByAddressCity(search);
        if (recruits.isEmpty()){
            recruits=recruitRepository.findByCompanyCompanyFnameContainingIgnoreCase(search);
        }
        if (recruits.isEmpty()){
            recruits=recruitRepository.findByProfessionContainingIgnoreCase(search);
        }
        if (recruits.isEmpty()){
            recruits=recruitRepository.findByPositionContainingIgnoreCase(search);
        }
        ModelMapper modelMapper=new ModelMapper();
        return recruits.stream().map(recruit -> modelMapper.map(recruit, RecruitDetailDto.class)).collect(Collectors.toList());
    }

    @Override
    public Recruit findRecruitById(int recruit_id) {
        return recruitRepository.findById(recruit_id).get();
    }
}
