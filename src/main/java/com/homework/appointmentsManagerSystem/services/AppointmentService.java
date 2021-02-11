package com.homework.appointmentsManagerSystem.services;

import com.homework.appointmentsManagerSystem.dtos.AppointmentApplicationDto;
import com.homework.appointmentsManagerSystem.dtos.AppointmentInfoDto;
import com.homework.appointmentsManagerSystem.dtos.AppointmentInfoRequest;
import com.homework.appointmentsManagerSystem.dtos.SpecialistDTO;
import com.homework.appointmentsManagerSystem.entities.Appointment;
import com.homework.appointmentsManagerSystem.entities.Customer;
import com.homework.appointmentsManagerSystem.entities.Specialist;
import com.homework.appointmentsManagerSystem.entities.enums.State;
import com.homework.appointmentsManagerSystem.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final SpecialistService specialistService;
    private final CustomerService customerService;

    public AppointmentService(AppointmentRepository appointmentRepository, SpecialistService specialistService,
                              CustomerService customerService) {
        this.appointmentRepository = appointmentRepository;
        this.specialistService = specialistService;
        this.customerService = customerService;
    }

    public Appointment createAndSaveAppointmentInformation(AppointmentApplicationDto appointmentApplicationDto) {
        Customer customer = createCustomer(appointmentApplicationDto);
        Appointment appointment = new Appointment();
        appointment.setSpecialist(retrieveSpecialistById(appointmentApplicationDto.getSpecialistId()));
        appointment.setCustomer(customer);
        appointment.setGeneratedCode(generatePin());
        appointment.setAppTimeFrom(getNextAvailableAppointment(appointmentApplicationDto.getSpecialistId()));
        appointment.setAppTimeTo(getNextAvailableAppointment(appointmentApplicationDto
                .getSpecialistId()).plusMinutes(30));
        appointmentRepository.saveAndFlush(appointment);
        return appointment;
    }

    private Appointment getAppointmentById(Long id) {
        return appointmentRepository.getOne(id);
    }


    public List<SpecialistDTO> specialistDTOList(List<Specialist> specialists) {
        return specialists.stream()
                .map(sp -> new SpecialistDTO(sp.getId(),
                        sp.getFirstName(),
                        sp.getLastName(),
                        sp.getSpecialization(),
                        sp.getAppointments()))
                .collect(Collectors.toList());
    }


    public AppointmentInfoDto getAppointmentInfo(Appointment appointment) {
        Long id = appointment.getId();
        int generatedCode = appointment.getGeneratedCode();
        String timeLeftTillAppointment = timeLeftBeforeTheVisit(appointment);
        return new AppointmentInfoDto(id, generatedCode,
                positionInLine(generatedCode), timeLeftTillAppointment);

    }

    public Appointment findAppointment(AppointmentInfoRequest appointmentInfoRequest) {
        Appointment appointment = appointmentRepository.findByGeneratedCode(appointmentInfoRequest.getPinCode());
        return checkIfAppointmentExist(appointment);
    }


    private Appointment checkIfAppointmentExist(Appointment appointment) {
        if (appointment == null) {
            throw new NullPointerException("Appointment not found");
        } else return appointment;
    }

    public void removeAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public void updateAppointmentState(Long id, State state) {
        Appointment appointment = getAppointmentById(id);
        appointment.setState(state);
        appointmentRepository.save(appointment);
    }

    private int positionInLine(int pinCode) {
        Appointment appointment = appointmentRepository.findByGeneratedCode(pinCode);
        List<Appointment> appointmentList = appointment.getSpecialist().getAppointments();
        if (appointmentList.contains(appointment)) {
            return appointmentList.indexOf(appointment) + 1;
        } else return appointmentList.size() + 1;
    }

    private String timeLeftBeforeTheVisit(Appointment appointment) {
        LocalDateTime lastAppointmentTime = appointment.getAppTimeFrom();
        Duration duration = Duration.between(LocalDateTime.now(), lastAppointmentTime);
        return String.format("%d%s:%02d%s:%02d%s:%02d%s",
                duration.toDays(), "d",
                duration.toHours(), "h",
                duration.toMinutesPart(), "m",
                duration.toSecondsPart(), "s");
    }

    private LocalDateTime getNextAvailableAppointment(Long specialistId) {
        Optional<Appointment> lastAppointment = getSpecialistLastAppointment(specialistId);
        return lastAppointment
                .filter(date -> date.getAppTimeTo().isAfter(LocalDateTime.now()))
                .map(Appointment::getAppTimeTo)
                .orElseGet(this::calculatePossibleAppointmentTime);
    }

    private Optional<Appointment> getSpecialistLastAppointment(Long specialistID) {
        Specialist specialist = retrieveSpecialistById(specialistID);
        return specialist.getAppointments().stream()
                .max(Comparator.comparing(Appointment::getAppTimeTo));
    }

    private Customer createCustomer(AppointmentApplicationDto appointmentApplicationDto) {
        Customer customer = new Customer();
        customer.setId(appointmentApplicationDto.getId());
        customer.setFirstName(appointmentApplicationDto.getFirstName());
        customer.setLastName(appointmentApplicationDto.getLastName());
        customer.setBirthDate(appointmentApplicationDto.getBirthDate());
        customerService.saveCustomer(customer);
        return customer;
    }

    private LocalDateTime calculatePossibleAppointmentTime() {
        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)
                .plusMinutes(30 * (LocalDateTime.now().getMinute() / 30)).plusMinutes(30));
        return LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)
                .plusMinutes(30 * (LocalDateTime.now().getMinute() / 30)).plusMinutes(30);
    }

    private int generatePin() {
        Random random = new Random();
        return Integer.parseInt(format("%04d", random.nextInt(10000)));
    }

    private Specialist retrieveSpecialistById(Long id) {
        return specialistService.getSpecialistById(id);
    }
}
