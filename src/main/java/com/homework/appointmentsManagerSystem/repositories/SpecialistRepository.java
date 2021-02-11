package com.homework.appointmentsManagerSystem.repositories;

import com.homework.appointmentsManagerSystem.entities.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    Specialist findByEmail(String email);
}

