package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.CompanyDto;
import com.project.cv.Dto.RegisterDto;
import com.project.cv.Dto.UserDto;
import com.project.cv.Dto.UsersDto;
import com.project.cv.Model.*;
import com.project.cv.Repository.CandidateRepository;
import com.project.cv.Repository.CompanyRepository;
import com.project.cv.Repository.RoleRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public boolean addRegisterUser(RegisterDto registerDto) {
        try{
            User user=new User();
            Role role=roleRepository.findRoleByName(registerDto.getRoleName());
            if(userRepository.findUserByName(registerDto.getUsername())!=null){
                return false;
            }
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setFname(registerDto.getFname());
            user.setImage(registerDto.getImage());
            user.setEmail(registerDto.getEmail());
            user.setPhone(registerDto.getPhone());
            user.setWebsite(registerDto.getWebsite());
            user.addRole(role);

            if (registerDto.getRoleName().equals("company")){
                Company company=new Company();
                company.setCompany(user);
                company.setFoundedAt(registerDto.getBirthday());
                companyRepository.save(company);
            } else if (registerDto.getRoleName().equals("candidate")) {
                Candidates candidates=new Candidates();
                candidates.setCandidate(user);
                candidates.setBirthday(registerDto.getBirthday());
                candidateRepository.save(candidates);
            }
            userRepository.save(user);
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("Register User Error: "+e.getMessage());
            return false;
        }
    }

    @Override
    public User updateUser(UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        user.setFname(userDto.getFname());
        user.setImage(userDto.getImage());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setWebsite(userDto.getWebsite());
        user.setAddress(userDto.getAddress());
        return userRepository.save(user);
    }

    @Override
    public User getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findUserByName(authentication.getName());
    }

    @Override
    public UsersDto getInformationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=userRepository.findUserByName(authentication.getName());
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(user,UsersDto.class);
    }

}
