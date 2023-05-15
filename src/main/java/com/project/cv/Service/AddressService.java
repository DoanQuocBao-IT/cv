package com.project.cv.Service;

import com.project.cv.Dto.AddressDto;
import com.project.cv.Model.Address;

import java.util.List;

public interface AddressService {
    Address createAddress(AddressDto addressDto);
    Address updateAddress(int address_id,AddressDto addressDto);
    AddressDto findAddressById(int address_id);
    List<AddressDto> allAddressForUser();
    List<AddressDto> allAddressForCompany(int company_id);
}
