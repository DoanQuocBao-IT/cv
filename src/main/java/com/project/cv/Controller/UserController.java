package com.project.cv.Controller;

import com.project.cv.Dto.CompaniesDto;
import com.project.cv.Dto.RecruitDetailDto;
import com.project.cv.Model.Address;
import com.project.cv.Model.Company;
import com.project.cv.Model.Cv;
import com.project.cv.Model.Recruit;
import com.project.cv.Service.AddressService;
import com.project.cv.Service.CompanyService;
import com.project.cv.Service.CvService;
import com.project.cv.Service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appcv")
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    RecruitService recruitService;
    @Autowired
    CvService cvService;
    @Autowired
    CompanyService companyService;
    @Autowired
    AddressService addressService;
    @GetMapping("/recruit")
    public List<RecruitDetailDto> findAllRecruit(){
        return recruitService.findAllRecruit();
    }
    @GetMapping("/top6/recruit")
    public List<RecruitDetailDto> findTop6Recruit(){
        return recruitService.findTop6RecruitNewest();
    }
    @GetMapping("/recruit/{recruit_id}")
    public Recruit findRecruitById(@PathVariable int recruit_id){
        return recruitService.findRecruitById(recruit_id);
    }
    @GetMapping("/company/{company_id}")
    public Company findCompanyById(@PathVariable int company_id){
        return companyService.getCompanyById(company_id);
    }
    @GetMapping("/all/company")
    public List<CompaniesDto>  findAllCompanies(){
        return companyService.findAllCompany();
    }
    @GetMapping("/job")
    public List<RecruitDetailDto> findByCity(@RequestParam String search){
        return recruitService.findAllRecruitBySearch(search);
    }
}
