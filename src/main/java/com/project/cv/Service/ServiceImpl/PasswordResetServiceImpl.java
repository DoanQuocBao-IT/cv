package com.project.cv.Service.ServiceImpl;

import com.project.cv.Model.PasswordResetPassCode;
import com.project.cv.Model.User;
import com.project.cv.Repository.PasswordResetPasscodeRepository;
import com.project.cv.Repository.UserRepository;
import com.project.cv.Service.EmailSenderService;
import com.project.cv.Service.PasswordResetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordResetPasscodeRepository passwordResetPasscodeRepository;
    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void sendPasswordResetMail(String email) {
        User user=userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Email not found "+email);
        }

        PasswordResetPassCode passcode= new PasswordResetPassCode(user);
        passwordResetPasscodeRepository.save(passcode);
        String subject = "Reset Your FindJob Password";
        String body = "Dear "+user.getFname()+",\n\nWe have received a request to reset your password for your FindJob account. To proceed with the password reset, please use the following Passcode within the FindJob application:\n\nPasscode: " + passcode.getPasscode()+
                "\n\nIf you did not request a password reset, please ignore this email. Your account will remain secure.\n\nPlease note that the Passcode is valid for a limited time period. Make sure to reset your password within this timeframe to ensure successful account recovery.\n\n" +
                "If you have any questions or need further assistance, please contact our support team at doanquocbao2002@gmail.com .\n\nThank you for using FindJob.\n\nBest regards,\nFindJob";


        // Send email
        emailSenderService.sendEmailActive(email,subject,body);
    }
    @Transactional
    @Override
    public void resetPassword(String passcode, String password) {
        PasswordResetPassCode passwordResetToken= passwordResetPasscodeRepository.findByPasscode(passcode);
        if (passwordResetToken == null) {
            throw new IllegalArgumentException("Invalid passcode");
        }
        if (passwordResetToken.isTokenExpired()) {
            throw new IllegalArgumentException("Passcode has expired");
        }
        User user=passwordResetToken.getUser();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        passwordResetPasscodeRepository.deleteByPasscode(passcode);
    }
}
