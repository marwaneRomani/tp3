package com.marwane.tp3.services;

import com.marwane.tp3.entities.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    void savePatient(Patient patient);
    void deletePatient(Long id);
}
