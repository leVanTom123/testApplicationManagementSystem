package com.homework.appointmentsManagerSystem.dtos;

public class AppointmentInfoDto {

    private Long id;

    private int pinCode;

    private int placeInLine;

    private String timeLeftTillAppointment;

    public AppointmentInfoDto() {
    }

    public AppointmentInfoDto(Long id, int pinCode, int placeInLine, String timeLeftTillAppointment) {
        this.id = id;
        this.pinCode = pinCode;
        this.placeInLine = placeInLine;
        this.timeLeftTillAppointment = timeLeftTillAppointment;
    }

    public Long getId() {
        return id;
    }

    public int getPinCode() {
        return pinCode;
    }

    public int getPlaceInLine() {
        return placeInLine;
    }

    public String getTimeLeftTillAppointment() {
        return timeLeftTillAppointment;
    }

    @Override
    public String toString() {
        return "AppointmentInfoDto{" +
                "pinCode=" + pinCode +
                ", placeInLine=" + placeInLine +
                ", timeLeftTillAppointment='" + timeLeftTillAppointment + '\'' +
                '}';
    }
}
