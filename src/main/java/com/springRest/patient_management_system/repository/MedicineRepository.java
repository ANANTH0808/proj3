package com.springRest.patient_management_system.repository;

import com.springRest.patient_management_system.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> { // Use Long here
}
   