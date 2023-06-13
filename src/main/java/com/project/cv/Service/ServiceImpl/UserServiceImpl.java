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
            if (registerDto.getFname()!=null)
                user.setFname(registerDto.getFname());
            if (registerDto.getImage()!=null)
                user.setImage(registerDto.getImage());
            if (registerDto.getEmail()!=null)
                user.setEmail(registerDto.getEmail());
            if (registerDto.getPhone()!=null)
                user.setPhone(registerDto.getPhone());
            if (registerDto.getWebsite()!=null)
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
        if (userDto.getFname()!=null)
            user.setFname(userDto.getFname());
        if (userDto.getImage()!=null)
            user.setImage(userDto.getImage());
        if (userDto.getEmail()!=null)
            user.setEmail(userDto.getEmail());
        if (userDto.getPhone()!=null)
            user.setPhone(userDto.getPhone());
        if (userDto.getWebsite()!=null)
            user.setWebsite(userDto.getWebsite());
        if (userDto.getAddress()!=null)
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

    @Override
    public void changePassword(String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

}
