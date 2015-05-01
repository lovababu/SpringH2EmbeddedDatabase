package com.lova.spring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Lovababu on 4/26/2015.
 */

@Entity
@Table(name = "SAMPLE")
public class SampleDomain {

    @Setter @Getter
    @Id
    @Column(name = "SID")
    private Long id;

    @Setter @Getter
    @Column(name = "NAME")
    private String name;

    @Setter @Getter
    @Column(name = "ADDRESS")
    private String address;
}
