package com.crypto.dashboardweb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ExchangeAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String color;
    private String logo;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<ExchangeAccountApi> exchangeAccountApis;
}
