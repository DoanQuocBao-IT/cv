package com.project.cv.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recruit")
public class Recruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    private String profession;
    private String salary;
    private String position;
    private int inventory;
    private String gender;
    private String experience;

    private String responsibilities;
    private String qualifications;
    private String interests;

    @OneToOne
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties("user")
    private Address address;
    private Date fromDate;
    private Date toDate;
    public long getCountdown(){
        LocalDate localFromDate = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localToDate = this.toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(localFromDate, localToDate);
    }
}
