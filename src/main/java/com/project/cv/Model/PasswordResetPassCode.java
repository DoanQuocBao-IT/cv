package com.project.cv.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "password_reset_passcode")
public class PasswordResetPassCode {
    private static final int EXPIRATION = 60*24;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String passcode;
    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    private Date expiryDate;

    public PasswordResetPassCode(final User user){
        super();
        this.user=user;
        this.expiryDate=calculateExpiryDate(EXPIRATION);
        Random random = new Random();
        int confirmationCode = random.nextInt(999999);
        this.passcode=Integer.toString(confirmationCode);
    }
    public PasswordResetPassCode(final String passcode){
        super();
        this.passcode=passcode;
    }
    public boolean isTokenExpired() {
        return expiryDate.before(new Date());
    }
    private Date calculateExpiryDate(final int expiryTimeMinutes){
        final Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiryTimeMinutes);
        return new Date(calendar.getTime().getTime());
    }
}
