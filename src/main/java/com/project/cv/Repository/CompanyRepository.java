package com.project.cv.Repository;

import com.project.cv.Model.Company;
import com.project.cv.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findByCompany(User company);
    List<Company> findTop6ByOrderByInventoryJobDesc();
}
