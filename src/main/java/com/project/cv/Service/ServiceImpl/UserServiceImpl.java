package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.RegisterDto;
import com.project.cv.Dto.UserDto;
import com.project.cv.Model.Gender;
import com.project.cv.Model.Role;
import com.project.cv.Model.User;
import com.project.cv.Repository.RoleRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.UserService;
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
            user.setBirthday(registerDto.getBirthday());

            if (registerDto.getGender().equals("male")) {
                user.setGender(Gender.male.getGender());
            } else if (registerDto.getGender().equals("female")) {
                user.setGender(Gender.female.getGender());
            } else {
                user.setGender(Gender.other.getGender());
            }
            user.setPhone(registerDto.getPhone());
            user.setWebsite(registerDto.getWebsite());
            user.addRole(role);
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
        user.setBirthday(userDto.getBirthday());

        if (userDto.getGender().equals("male")) {
            user.setGender(Gender.male.getGender());
        } else if (userDto.getGender().equals("female")) {
            user.setGender(Gender.female.getGender());
        } else {
            user.setGender(Gender.other.getGender());
        }
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
}
