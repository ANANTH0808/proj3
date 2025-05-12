package com.springRest.patient_management_system.service;

import com.springRest.patient_management_system.entity.Medicine;
import com.springRest.patient_management_system.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    private MedicineRepository medicineRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> getAllMedicines() { // Ensure the method name is correct
        return medicineRepository.findAll();
    }

    public void save(Medicine medicine) {
        medicineRepository.save(medicine);
    }

    public Medicine findById(Long id) { // Use Long here
        return medicineRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) { // Use Long here
        medicineRepository.deleteById(id);
    }
}
   