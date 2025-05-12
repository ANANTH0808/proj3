package com.springRest.patient_management_system.controller;

import com.springRest.patient_management_system.entity.Appointment;
import com.springRest.patient_management_system.entity.Doctor;
import com.springRest.patient_management_system.entity.Patient;
import com.springRest.patient_management_system.service.AppointmentService;
import com.springRest.patient_management_system.service.DoctorService;
import com.springRest.patient_management_system.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/list")
    public String listAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments/list";
    }

    @GetMapping("/add")
    public String getAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "appointments/add";
    }

    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment, Model model) {
        try {
            appointmentService.save(appointment);
            return "redirect:/appointments/list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("patients", patientService.getAllPatients());
            model.addAttribute("doctors", doctorService.getAllDoctors());
            return "appointments/add";
        }
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("appointmentId") Long id, Model model) {
        Appointment appointment = appointmentService.findById(id);
        if (appointment == null) {
            model.addAttribute("error", "Appointment not found");
            return "redirect:/appointments/list";
        }
        model.addAttribute("appointment", appointment);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "appointments/add";
    }

    @GetMapping("/delete")
    public String deleteAppointment(@RequestParam("appointmentId") Long id) {
        appointmentService.deleteById(id);
        return "redirect:/appointments/list";
    }
}