package com.project.cv.Service;

import com.project.cv.Dto.CreateCvDto;
import com.project.cv.Model.Cv;

import java.util.List;

public interface CvService {
    Cv addCV(CreateCvDto cv);
    Cv updateCV(int id, CreateCvDto cvDto);
    Cv allCV();
}
