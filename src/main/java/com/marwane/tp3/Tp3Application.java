package com.marwane.tp3;

import com.marwane.tp3.entities.Patient;
import com.marwane.tp3.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Tp3Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(new Patient(null, "John", new Date(), false, 120));
            patientRepository.save(new Patient(null, "Mary", new Date(), true, 340));
            patientRepository.save(new Patient(null, "Peter", new Date(), false, 230));
            patientRepository.save(new Patient(null, "Susan", new Date(), true, 450));

            patientRepository.findAll().forEach(p -> {
                System.out.println(p.getNom());
            });
        };
    }

}
