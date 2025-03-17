package com.marwane.tp3.services;

import com.marwane.tp3.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    void savePatient(Patient patient);
    void deletePatient(Long id);

    Page<Patient> findByNomContains(String keyword, Pageable pageable);
}
