package com.bit.contract.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String division;
    @Column
    private String object;
    @Column
    private String carnum;
    @Column
    private String caruser;
    @Column
    private Date startdate;
    @Column
    private Date enddate;
    @Column
    private String period;
    @Column
    private Long monthlyfee;
    @Column
    private String client;
    @Column
    private String clientnumber;
    @Column
    private String files;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
