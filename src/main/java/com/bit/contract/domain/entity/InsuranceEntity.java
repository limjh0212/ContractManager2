package com.bit.contract.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "insurance")
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceEntity extends TimeStamp {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String object;
    @Column
    private Date startdate;
    @Column
    private Date enddate;
    @Column
    private String period;
    @Column
    private Long cost;
    @Column
    private Long precost;
    @Column
    private Long diffcost;
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
