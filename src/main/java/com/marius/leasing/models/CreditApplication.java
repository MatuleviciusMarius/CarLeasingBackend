package com.marius.leasing.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CreditApplication {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private BigDecimal requestedFundingAmount;

    @NotNull
    private Long leaseTermMonths;

    private Boolean isApproved;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Person.class)
    @JoinColumn(name = "personId", referencedColumnName = "id")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRequestedFundingAmount() {
        return requestedFundingAmount;
    }

    public void setRequestedFundingAmount(BigDecimal requestedFundingAmount) {
        this.requestedFundingAmount = requestedFundingAmount;
    }

    public Long getLeaseTermMonths() {
        return leaseTermMonths;
    }

    public void setLeaseTermMonths(Long leaseTermMonths) {
        this.leaseTermMonths = leaseTermMonths;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
