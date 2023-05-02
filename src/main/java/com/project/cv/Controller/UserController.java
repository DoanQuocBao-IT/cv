package com.project.cv.Controller;

import com.project.cv.Model.Cv;
import com.project.cv.Model.Recruit;
import com.project.cv.Service.CvService;
import com.project.cv.Service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/appcv")
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    RecruitService recruitService;
    @Autowired
    CvService cvService;
    @GetMapping("/recruit")
    public List<Recruit> findAllRecruit(){
        return recruitService.findAllRecruit();
    }
    @GetMapping("/cv")
    public List<Cv> findAllCv(){
        return cvService.findAllCv();
    }
}
