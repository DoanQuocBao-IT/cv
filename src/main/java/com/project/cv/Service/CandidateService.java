package com.project.cv.Service;

import com.project.cv.Dto.CandidateDto;
import com.project.cv.Model.Candidates;

public interface CandidateService {
    Candidates getProfileCandidate();
    Candidates getCandidateById(int candidate_id);
    Candidates updateCandidate(CandidateDto candidateDto);

}
