package com.bit.contract.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "notice")
@NoArgsConstructor
public class NoticeEntity extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int section;
    @Column
    private Long sectionid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
