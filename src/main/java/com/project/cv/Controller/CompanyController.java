package com.project.cv.Controller;

import com.project.cv.Dto.AddressDto;
import com.project.cv.Dto.CreateCvDto;
import com.project.cv.Dto.RecruitDto;
import com.project.cv.Dto.UserDto;
import com.project.cv.Model.Address;
import com.project.cv.Model.Cv;
import com.project.cv.Model.Recruit;
import com.project.cv.Model.User;
import com.project.cv.Service.AddressService;
import com.project.cv.Service.RecruitService;
import com.project.cv.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = {"*"})
public class CompanyController {
    @Autowired
    UserService userService;
    @Autowired
    RecruitService recruitService;
    @Autowired
    AddressService addressService;

    @PostMapping("/create/recruit")
    public Recruit createRecruit(@RequestBody RecruitDto recruitDto){
        return recruitService.addRecruit(recruitDto);
    }
    @PutMapping("/update/recruit/{recruit_id}")
    public Recruit updateRecruit(@RequestBody RecruitDto recruitDto, @PathVariable int recruit_id){
        return recruitService.updateRecruit(recruit_id,recruitDto);
    }
    @GetMapping("/all/recruit")
    public List<Recruit> allCV(){
        return recruitService.findAllRecruit();
    }
    @GetMapping("/all/address")
    public List<Address> findAllAddressForUser(){
        return addressService.allAddressForUser();
    }
    @PostMapping("/create/address")
    public Address createAddress(@RequestBody AddressDto addressDto){
        return addressService.createAddress(addressDto);
    }
    @PutMapping("/update/address/{address_id}")
    public Address updateAddress(@RequestBody AddressDto addressDto,@PathVariable int address_id){
        return addressService.updateAddress(address_id,addressDto);
    }
    @PostMapping("/update/profile")
    public User updateProfile(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
    @GetMapping("/profile")
    public User getProfile(){
        return userService.getProfile();
    }
}
