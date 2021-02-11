package com.homework.appointmentsManagerSystem.services;

import com.homework.appointmentsManagerSystem.dtos.SpecialistAppointmentInfoDto;
import com.homework.appointmentsManagerSystem.entities.Specialist;
import com.homework.appointmentsManagerSystem.repositories.SpecialistRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialistService {

    private final SpecialistRepository specialistRepository;

    public SpecialistService(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
    }

    public List<Specialist> getSpecialistList() {
        return specialistRepository.findAll();
    }

    public Specialist getSpecialistById(Long id) {
        return specialistRepository.getOne(id);
    }


    public List<SpecialistAppointmentInfoDto> getSpecialistAppointmentsInfo() {
        String email = getLoggedInUserEmail();
        Specialist specialist = specialistRepository.findByEmail(email);
        return specialist.getAppointments().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private SpecialistAppointmentInfoDto toDto(com.homework.appointmentsManagerSystem.entities.Appointment app) {
        return new SpecialistAppointmentInfoDto(
                app.getCustomer().getFirstName(),
                app.getCustomer().getLastName(),
                app.getCustomer().getBirthDate(),
                app.getId(),
                app.getAppTimeFrom(),
                app.getAppTimeTo(),
                app.getState());
    }

    private String getLoggedInUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }
}
