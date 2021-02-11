package com.homework.appointmentsManagerSystem.entities;

import com.homework.appointmentsManagerSystem.entities.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "time_from")
    private LocalDateTime appTimeFrom;
    @Column(name = "time_to")
    private LocalDateTime appTimeTo;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state = State.NOTSTARTED;
    @Column(name = "generated_code", unique = true)
    private int generatedCode;
    @JsonIgnore
    @ManyToOne(fetch = EAGER,
            cascade = ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @JsonIgnore
    @ManyToOne(fetch = LAZY,
            cascade = {})
    @JoinColumn(name = "specialist_id", nullable = false)
    private Specialist specialist;

    public Appointment() {
    }

    public Appointment(Long id, LocalDateTime appTimeFrom, LocalDateTime appTimeTo, State state, int generatedCode, Customer customer, Specialist specialist) {
        this.id = id;
        this.appTimeFrom = appTimeFrom;
        this.appTimeTo = appTimeTo;
        this.state = state;
        this.generatedCode = generatedCode;
        this.customer = customer;
        this.specialist = specialist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppTimeFrom() {
        return appTimeFrom;
    }

    public void setAppTimeFrom(LocalDateTime appTimeFrom) {
        this.appTimeFrom = appTimeFrom;
    }

    public LocalDateTime getAppTimeTo() {
        return appTimeTo;
    }

    public void setAppTimeTo(LocalDateTime appTimeTo) {
        this.appTimeTo = appTimeTo;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getGeneratedCode() {
        return generatedCode;
    }

    public void setGeneratedCode(int generatedCode) {
        this.generatedCode = generatedCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appTimeFrom=" + appTimeFrom +
                ", appTimeTo=" + appTimeTo +
                ", state=" + state +
                ", generatedCode=" + generatedCode +
                ", customer=" + customer +
                ", specialist=" + specialist +
                '}';
    }
}
