package com.homework.appointmentsManagerSystem.dtos;

public class AppointmentInfoRequest {

    private String firstName;
    private String lastName;
    private int pinCode;

    public AppointmentInfoRequest() {
    }

    public AppointmentInfoRequest(String firstName, String lastName, int pinCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pinCode = pinCode;
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

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}

