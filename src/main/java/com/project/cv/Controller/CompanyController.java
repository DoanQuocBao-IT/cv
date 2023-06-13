package com.project.cv.Controller;

import com.project.cv.Dto.*;
import com.project.cv.Model.*;
import com.project.cv.Service.*;
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
    @Autowired
    CompanyService companyService;
    @Autowired
    CandidateService candidateService;
    @Autowired
    ApplyService applyService;

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
        return recruitService.findAllRecruitForCompany();
    }
    @PostMapping("/update/profile")
    public User updateProfile(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
    @PostMapping("/update/company")
    public Company updateCompany(@RequestBody CompanyDto companyDto){
        return companyService.updateCompany(companyDto);
    }
    @GetMapping("/profile")
    public User getProfile(){
        return userService.getProfile();
    }
    @GetMapping("/company/{company_id}")
    public Candidates getCandidateById(@PathVariable int candidate_id){
        return candidateService.getCandidateById(candidate_id);
    }
    @GetMapping("/information")
    public Company getInformationCompany()
    {
        return companyService.getInformationCompany();
    }
    @GetMapping("/apply/recruit/{recruit_id}")
    public int countApplyCV(@PathVariable int recruit_id){
        return applyService.countApplyCv(recruit_id);
    }
    @GetMapping("/cv/apply/recruit/{recruit_id}")
    public List<Cv> allCvApplyByRecruit(@PathVariable int recruit_id){
        return applyService.allCvApplyByRecruitId(recruit_id);
    }
    @GetMapping("/cv/apply/approved/recruit/{recruit_id}")
    public List<Cv> allCvApproved(@PathVariable int recruit_id){
        return applyService.allCvApplyByRecruitIdApproved(recruit_id);
    }
    @GetMapping("/cv/apply/pending/recruit/{recruit_id}")
    public List<Cv> allCvPending(@PathVariable int recruit_id){
        return applyService.allCvApplyByRecruitIdPending(recruit_id);
    }
    @GetMapping("/cv/{cv_id}/approved/recruit/{recruit_id}")
    public void approvedCvApplyRecruit(@PathVariable int cv_id,@PathVariable int recruit_id){
        applyService.approvedCvApplyRecruit(recruit_id,cv_id);
    }
}
