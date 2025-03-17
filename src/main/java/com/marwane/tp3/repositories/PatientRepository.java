package com.marwane.tp3.repositories;


import com.marwane.tp3.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContains(String keyword);
}
