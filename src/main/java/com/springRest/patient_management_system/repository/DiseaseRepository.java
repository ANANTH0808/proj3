package com.springRest.patient_management_system.repository;

import com.springRest.patient_management_system.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    @Query("SELECT d FROM Disease d LEFT JOIN FETCH d.patients")
    List<Disease> findAllWithPatients();
}