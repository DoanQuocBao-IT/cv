package com.project.cv.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties({"username","password","roles"})
    User candidate;
    private Date birthday;
    public String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(this.birthday);
        return formattedDate;
    }
    private String gender;
    public int getAge() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int age = 0;
        try {
            Date dateOfBirth = formatter.parse(formatter.format(birthday));
            age = currentDate.getYear() - dateOfBirth.getYear();
            if (currentDate.getMonth() < dateOfBirth.getMonth()
                    || (currentDate.getMonth() == dateOfBirth.getMonth()
                    && currentDate.getDate() < dateOfBirth.getDate())) {
                age--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age;
    }
    private String introduce;
    private String hobby;
    private String certificate;
}
