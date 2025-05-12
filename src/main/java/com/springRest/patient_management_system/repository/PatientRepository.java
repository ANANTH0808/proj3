package com.springRest.patient_management_system.repository;

import com.springRest.patient_management_system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
