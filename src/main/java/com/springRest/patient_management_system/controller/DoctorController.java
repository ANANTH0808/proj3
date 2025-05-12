package com.springRest.patient_management_system.controller;

import com.springRest.patient_management_system.entity.Doctor;
import com.springRest.patient_management_system.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/list")
    public String listDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "doctors/list";
    }

    @GetMapping("/add")
    public String getDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctors/addDoctor";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/doctors/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("doctorId") Long id, Model model) {
        Doctor doctor = doctorService.findById(id);
        if (doctor == null) {
            model.addAttribute("error", "Doctor not found");
            return "redirect:/doctors/list";
        }
        model.addAttribute("doctor", doctor);
        return "doctors/addDoctor";
    }

    @GetMapping("/delete")
    public String deleteDoctor(@RequestParam("doctorId") Long id) {
        doctorService.deleteById(id);
        return "redirect:/doctors/list";
    }
}