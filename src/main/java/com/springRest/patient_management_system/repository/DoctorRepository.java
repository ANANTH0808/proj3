package com.springRest.patient_management_system.repository;

import com.springRest.patient_management_system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> { // Use Long here
}
   