package com.homework.appointmentsManagerSystem.repositories;

import com.homework.appointmentsManagerSystem.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByGeneratedCode(int generatedCode);
}
