package com.springRest.patient_management_system.controller;

import com.springRest.patient_management_system.entity.Disease;
import com.springRest.patient_management_system.entity.Patient;
import com.springRest.patient_management_system.service.DiseaseService;
import com.springRest.patient_management_system.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/diseases")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public String listDiseases(Model model) {
        List<Disease> diseases = diseaseService.getAllDiseasesWithPatients();
        model.addAttribute("diseases", diseases);
        return "diseases/list";
    }

    @GetMapping("/add")
    public String getDiseaseForm(Model model) {
        model.addAttribute("disease", new Disease());
        return "diseases/add";
    }

    @PostMapping("/save")
    public String saveDisease(@ModelAttribute("disease") Disease disease) {
        System.out.println("Saving disease: " + disease.getName());
        disease.setPatients(new HashSet<>()); // Ensure no patients are associated
        diseaseService.save(disease);
        System.out.println("Disease saved without patients");
        return "redirect:/diseases/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("diseaseId") Long id, Model model) {
        Disease disease = diseaseService.findById(id);
        if (disease == null) {
            model.addAttribute("error", "Disease not found");
            return "redirect:/diseases/list";
        }
        model.addAttribute("disease", disease);
        return "diseases/add";
    }

    @GetMapping("/delete")
    public String deleteDisease(@RequestParam("diseaseId") Long id) {
        diseaseService.deleteById(id);
        return "redirect:/diseases/list";
    }
}