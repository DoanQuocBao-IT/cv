package com.project.cv.Repository;

import com.project.cv.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(value = "SELECT r FROM Role r WHERE r.name=:name")
    Role findRoleByName(String name);
}
