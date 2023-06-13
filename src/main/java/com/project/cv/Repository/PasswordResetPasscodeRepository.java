package com.project.cv.Repository;

import com.project.cv.Model.PasswordResetPassCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetPasscodeRepository extends JpaRepository<PasswordResetPassCode, Integer> {
    PasswordResetPassCode findByPasscode(String token);
    void deleteByPasscode(String passcode);
}
