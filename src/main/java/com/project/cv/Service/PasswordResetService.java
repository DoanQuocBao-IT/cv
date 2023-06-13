package com.project.cv.Service;

public interface PasswordResetService {
    void sendPasswordResetMail(String email);
    void resetPassword(String token,String password);

}
