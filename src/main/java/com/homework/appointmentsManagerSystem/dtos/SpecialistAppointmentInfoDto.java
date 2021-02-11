package com.homework.appointmentsManagerSystem.dtos;

import com.homework.appointmentsManagerSystem.entities.enums.State;

import java.time.LocalDateTime;
import java.util.Date;

public class SpecialistAppointmentInfoDto {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Long appointmentId;
    private LocalDateTime appTimeFrom;
    private LocalDateTime appTimeTo;
    private State state;

    public SpecialistAppointmentInfoDto() {
    }

    public SpecialistAppointmentInfoDto(String firstName, String lastName, Date birthDate,
                                        Long appointmentId, LocalDateTime appTimeFrom,
                                        LocalDateTime appTimeTo, State state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.appointmentId = appointmentId;
        this.appTimeFrom = appTimeFrom;
        this.appTimeTo = appTimeTo;
        this.state = state;
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

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
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

    public boolean isSelectedState(State state) {
        return this.state == state;
    }
}
