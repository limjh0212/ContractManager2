package com.bit.contract.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class CmntEntity extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int section;
    @Column
    private Long secid;
    @Column
    @ColumnDefault("0")
    private Long cmntid;
    @Column
    @ColumnDefault("0")
    private int depth;
    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}

