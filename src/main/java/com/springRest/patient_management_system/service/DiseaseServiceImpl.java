package com.springRest.patient_management_system.service;

import com.springRest.patient_management_system.entity.Disease;
import com.springRest.patient_management_system.entity.Patient;
import com.springRest.patient_management_system.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseServiceImpl extends DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public List<Disease> getAllDiseasesWithPatients() {
        List<Disease> diseases = diseaseRepository.findAllWithPatients();
        System.out.println("Fetched " + diseases.size() + " diseases");
        for (Disease disease : diseases) {
            System.out.println("Disease: " + disease.getName() + ", ID: " + disease.getId());
            System.out.println("  Patients: " + (disease.getPatients() != null ? disease.getPatients().size() : "null"));
            if (disease.getPatients() != null) {
                for (Patient patient : disease.getPatients()) {
                    System.out.println("    Patient ID: " + patient.getId() + ", Name: " + (patient.getName() != null ? patient.getName() : "null"));
                }
            }
        }
        return diseases;
    }

    @Override
    public Disease findById(Long id) {
        return diseaseRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Disease disease) {
        diseaseRepository.save(disease);
    }

    @Override
    public void deleteById(Long id) {
        diseaseRepository.deleteById(id);
    }
}