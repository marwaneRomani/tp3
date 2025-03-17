package com.marwane.tp3.services;

import com.marwane.tp3.entities.Patient;
import com.marwane.tp3.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public Page<Patient> findByNomContains(String keyword, Pageable pageable) {
        return patientRepository.findByNomContains(keyword, pageable);
    }
}