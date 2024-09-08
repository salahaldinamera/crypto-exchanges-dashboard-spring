package com.crypto.dashboardweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExchangeAccountApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String apiKey;
    private String apiSecret;
    private boolean demo;

    @ManyToOne()
    @JsonBackReference
    private ExchangeAccount exchangeAccountId;
}
