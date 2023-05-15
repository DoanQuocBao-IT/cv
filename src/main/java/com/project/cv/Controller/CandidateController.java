package com.project.cv.Controller;

import com.project.cv.Dto.*;
import com.project.cv.Model.*;
import com.project.cv.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin(origins = {"*"})
public class CandidateController {
    @Autowired
    UserService userService;
    @Autowired
    CvService cvService;
    @Autowired
    AddressService addressService;
    @Autowired
    CandidateService candidateService;
    @Autowired
    CompanyService companyService;
    @Autowired
    ApplyService applyService;

    @PostMapping("/create/cv")
    public Cv createCV(@RequestBody CreateCvDto createCvDto){
        return cvService.addCV(createCvDto);
    }
    @PutMapping("/update/cv/{cv_id}")
    public Cv updateCV(@RequestBody CreateCvDto createCvDto,@PathVariable int cv_id){
        return cvService.updateCV(cv_id,createCvDto);
    }
    @GetMapping("/all/cv")
    public Cv allCV(){
        return cvService.allCV();
    }

    @PostMapping("/update/profile")
    public User updateProfile(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
    @PostMapping("/update/candidate")
    public Candidates updateCandidate(@RequestBody CandidateDto candidateDto){
        return candidateService.updateCandidate(candidateDto);
    }
    @GetMapping("/profile")
    public User getProfile(){
        return userService.getProfile();
    }
    @GetMapping("/company/{company_id}")
    public Company getCompanyById(@PathVariable int company_id){
        return companyService.getCompanyById(company_id);
    }
    @GetMapping("/information")
    public Candidates getInformationCandidate()
    {
        return candidateService.getProfileCandidate();
    }
    @GetMapping("/apply/{recruit_id}")
    public Apply applyCvToRecruit(@PathVariable int recruit_id){
        return applyService.applyCvToRecruit(recruit_id);
    }
    @GetMapping("/recruit/apply")
    public List<RecruitDetailDto> findAllRecruitApply(){
        return applyService.allRecruitApply();
    }
    @GetMapping("/all/recruit/approved/cv/{cv_id}")
    public List<RecruitDetailDto> allRecruitApproved(@PathVariable int cv_id){
        return applyService.allRecruitApplyApproved(cv_id);
    }
}
