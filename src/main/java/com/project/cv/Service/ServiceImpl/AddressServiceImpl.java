package com.project.cv.Service.ServiceImpl;

import com.project.cv.Dto.AddressDto;
import com.project.cv.Model.Address;
import com.project.cv.Model.User;
import com.project.cv.Repository.AddressRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address createAddress(AddressDto addressDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Address address=new Address();
        address.setUser(user);
        address.setCity(addressDto.getCity());
        address.setAddress(addressDto.getAddress());
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(int address_id,AddressDto addressDto) {
        Address address=addressRepository.findById(address_id).get();
        address.setCity(addressDto.getCity());
        address.setAddress(addressDto.getAddress());
        return addressRepository.save(address);
    }

    @Override
    public List<AddressDto> allAddressForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        List<Address> addresses=addressRepository.findByUser(user);
        ModelMapper modelMapper=new ModelMapper();
        return addresses.stream().map(address -> modelMapper.map(address,AddressDto.class)).collect(Collectors.toList());
    }
}
