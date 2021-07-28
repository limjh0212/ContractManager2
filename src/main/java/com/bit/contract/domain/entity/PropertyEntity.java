package com.bit.contract.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "property")
@NoArgsConstructor
@AllArgsConstructor
public class PropertyEntity extends TimeStamp {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String division;
    @Column
    private String object;
    @Column
    private Date startdate;
    @Column
    private Date enddate;
    @Column
    private String period;
    @Column
    private int deposit;
    @Column
    private int monthlyfee;
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
