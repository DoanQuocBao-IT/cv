package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.CompaniesDto;
import com.project.cv.Dto.CompanyDto;
import com.project.cv.Model.Company;
import com.project.cv.Model.User;
import com.project.cv.Repository.CompanyRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company getInformationCompany() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        return companyRepository.findByCompany(user);
    }

    @Override
    public Company getCompanyById(int user_id) {
        return companyRepository.findById(user_id).get();
    }

    @Override
    public Company updateCompany(CompanyDto companyDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Company company=companyRepository.findByCompany(user);
        company.setFoundedAt(companyDto.getFoundedAt());
        company.setInformation(companyDto.getInformation());
        company.setField(companyDto.getField());
        return companyRepository.save(company);
    }

    @Override
    public List<CompaniesDto> findAllCompany() {
        List<Company> companies=companyRepository.findAll();
        ModelMapper modelMapper=new ModelMapper();
        return companies.stream().map(company -> modelMapper.map(company, CompaniesDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CompaniesDto> findTop6Company() {
        List<Company> companies=companyRepository.findTop6ByOrderByInventoryJobDesc();
        ModelMapper modelMapper=new ModelMapper();
        return companies.stream().map(company -> modelMapper.map(company, CompaniesDto.class)).collect(Collectors.toList());
    }
}
