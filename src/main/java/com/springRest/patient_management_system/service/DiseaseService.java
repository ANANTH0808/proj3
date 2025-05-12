package com.springRest.patient_management_system.service;

import com.springRest.patient_management_system.entity.Disease;
import com.springRest.patient_management_system.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    // Method to get all diseases
    public List<Disease> getAllDiseases() {
        return diseaseRepository.findAll(); // Fetch all diseases from the repository
    }

    public List<Disease> getAllDiseasesWithPatients() {
        return diseaseRepository.findAllWithPatients(); // Ensure this method is implemented in the repository
    }

    public void save(Disease disease) {
        diseaseRepository.save(disease);
    }

    public Disease findById(Long id) {
        return diseaseRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        diseaseRepository.deleteById(id);
    }
}
