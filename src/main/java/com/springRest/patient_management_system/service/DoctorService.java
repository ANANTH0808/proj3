package com.springRest.patient_management_system.service;

import com.springRest.patient_management_system.entity.Doctor;
import com.springRest.patient_management_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor findById(Long id) { // Use Long here
        return doctorRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) { // Use Long here
        doctorRepository.deleteById(id);
    }
}
   