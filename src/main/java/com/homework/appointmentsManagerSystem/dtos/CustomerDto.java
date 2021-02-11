package com.homework.appointmentsManagerSystem.dtos;

import com.homework.appointmentsManagerSystem.entities.Appointment;

import java.util.Date;
import java.util.List;


public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<Appointment> appointments;

    public CustomerDto(Long id, String firstName, String lastName, Date birthDate, List<Appointment> appointments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

}
