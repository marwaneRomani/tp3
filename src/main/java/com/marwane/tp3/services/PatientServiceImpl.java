package com.marwane.tp3.services;

import com.marwane.tp3.entities.Patient;
import com.marwane.tp3.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Page<Patient> findPatientsByName(String keyword, int page, int size) {
        return patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
    }

    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getPatient(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
