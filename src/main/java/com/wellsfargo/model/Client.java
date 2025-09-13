// Client.java
package com.wellsfargo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Assuming a name field for the client

    @ManyToOne
    @JoinColumn(name = "advisor_id", nullable = false)
    private FinancialAdvisor advisor;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Security> securities = new ArrayList<>(); // Represents the portfolio

    // Constructors
    public Client() {}

    public Client(String name, FinancialAdvisor advisor) {
        this.name = name;
        this.advisor = advisor;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FinancialAdvisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(FinancialAdvisor advisor) {
        this.advisor = advisor;
    }

    public List<Security> getSecurities() {
        return securities;
    }

    public void setSecurities(List<Security> securities) {
        this.securities = securities;
    }

    // Helper methods for managing securities
    public void addSecurity(Security security) {
        securities.add(security);
        security.setClient(this);
    }

    public void removeSecurity(Security security) {
        securities.remove(security);
        security.setClient(null);
    }
}
