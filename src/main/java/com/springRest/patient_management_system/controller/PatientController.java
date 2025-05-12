package com.springRest.patient_management_system.controller;

import com.springRest.patient_management_system.entity.Patient;
import com.springRest.patient_management_system.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public String listPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients/list";
    }

    @GetMapping("/add")
    public String getPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/add";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientService.save(patient);
        return "redirect:/patients/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("patientId") Long id, Model model) {
        Patient patient = patientService.findById(id);
        if (patient == null) {
            model.addAttribute("error", "Patient not found");
            return "redirect:/patients/list";
        }
        model.addAttribute("patient", patient);
        return "patients/add";
    }

    @GetMapping("/delete")
    public String deletePatient(@RequestParam("patientId") Long id) {
        patientService.deleteById(id);
        return "redirect:/patients/list";
    }
}