package com.project.cv.Controller;

import com.project.cv.Dto.AddressDto;
import com.project.cv.Dto.UsersDto;
import com.project.cv.Model.Address;
import com.project.cv.Service.AddressService;
import com.project.cv.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;
    @GetMapping("/profile")
    public UsersDto getInformationUser(){
        return userService.getInformationUser();
    }
    @GetMapping("/all/address")
    public List<AddressDto> findAllAddressForUser(){
        return addressService.allAddressForUser();
    }
    @GetMapping("/address/{address_id}")
    public AddressDto findAddressById(@PathVariable int address_id){
        return addressService.findAddressById(address_id);
    }
    @PostMapping("/create/address")
    public Address createAddress(@RequestBody AddressDto addressDto){
        return addressService.createAddress(addressDto);
    }
    @PutMapping("/update/address/{address_id}")
    public Address updateAddress(@RequestBody AddressDto addressDto,@PathVariable int address_id){
        return addressService.updateAddress(address_id,addressDto);
    }
}
