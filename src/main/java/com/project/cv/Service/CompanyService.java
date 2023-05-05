package com.project.cv.Service;

import com.project.cv.Dto.CompaniesDto;
import com.project.cv.Dto.CompanyDto;
import com.project.cv.Model.Company;

import java.util.List;

public interface CompanyService {
    Company getInformationCompany();
    Company getCompanyById(int company_id);
    Company updateCompany(CompanyDto companyDto);
    List<CompaniesDto> findAllCompany();
    List<CompaniesDto> findTop6Company();
}
