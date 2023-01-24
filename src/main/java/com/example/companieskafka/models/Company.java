/*
package com.example.companieskafka.models;

import lombok.Data;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @NotNull
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @NotNull
    @Unique
    @Column(name = "name")
    private String name;

    @NotNull
    @Unique
    @Column(name = "short_name")
    private String shortName;

    @Column(name = "ogrn")
    private int ogrn;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "deleted")
    private Boolean deleted;
}
*/
