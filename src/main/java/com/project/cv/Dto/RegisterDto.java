package com.project.cv.Dto;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class RegisterDto {
    private String username;
    private String password;
    private String fname;
    private String image;
    private String email;
    private String phone;
    private String website;
    private Date birthday;
    public String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(this.birthday);
        return formattedDate;
    }
    private String roleName;
}
