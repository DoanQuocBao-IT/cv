package com.project.cv.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private User user;
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
}
