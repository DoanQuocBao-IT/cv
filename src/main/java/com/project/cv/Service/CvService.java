package com.project.cv.Service;

import com.project.cv.Dto.CreateCvDto;
import com.project.cv.Model.Cv;

public interface CvService {
    Cv addCV(CreateCvDto cv);
    Cv updateCV(int id, CreateCvDto cvDto);
    Cv allCV();
}
