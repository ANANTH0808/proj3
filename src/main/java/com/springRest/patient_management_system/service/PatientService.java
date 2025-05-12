package com.springRest.patient_management_system.service;

import com.springRest.patient_management_system.entity.Patient;
import com.springRest.patient_management_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
}
