package com.project.cv.Repository;

import com.project.cv.Model.Company;
import com.project.cv.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findByCompany(User company);
}
