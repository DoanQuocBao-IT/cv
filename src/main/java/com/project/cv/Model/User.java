package com.project.cv.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String username;
    private String password;
    private String fname;
    private String image;
    private Date birthday;
    public String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(this.birthday);
        return formattedDate;
    }
    private String gender;
    private String phone;
    private String website;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany
    @JoinColumn(name = "jobs_id")
    private Cv jobs;
}
