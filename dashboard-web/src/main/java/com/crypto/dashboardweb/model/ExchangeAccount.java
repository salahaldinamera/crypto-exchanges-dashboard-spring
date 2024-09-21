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
    @JoinColumn(name = "exchange_account_id")
    @JsonManagedReference
    private List<ExchangeAccountApi> apis;

    public ExchangeAccountApi getDemoApi() {
        for (ExchangeAccountApi api : this.apis) {
            if (api.isDemo()) {
                return api;
            }
        }
        return null;
    }

    public ExchangeAccountApi getRealApi() {
        for (ExchangeAccountApi api : this.apis) {
            if (!api.isDemo()) {
                return api;
            }
        }
        return null;
    }
}
