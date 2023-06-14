package com.project.cv.Repository;

import com.project.cv.Model.Address;
import com.project.cv.Model.Company;
import com.project.cv.Model.Recruit;
import com.project.cv.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit,Integer> {
    List<Recruit> findByCompany(Company company);
    List<Recruit> findByAddressCity(String city);
    List<Recruit> findByCompanyCompanyFnameContainingIgnoreCase(String name);
    List<Recruit> findByPositionContainingIgnoreCase(String position);
    List<Recruit> findByProfessionContainingIgnoreCase(String profession);
    List<Recruit>  findTop6ByToDateGreaterThanOrderByToDateDesc(Date currentDate);
}
