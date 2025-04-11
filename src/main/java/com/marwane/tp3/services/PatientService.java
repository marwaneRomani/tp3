package com.marwane.tp3.services;

import com.marwane.tp3.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {

    Page<Patient> findPatientsByName(String keyword, int page, int size);
}
