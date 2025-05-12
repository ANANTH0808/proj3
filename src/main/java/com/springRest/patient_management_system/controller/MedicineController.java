package com.springRest.patient_management_system.controller;

import com.springRest.patient_management_system.entity.Medicine;
import com.springRest.patient_management_system.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/list")
    public String listMedicines(Model model) {
        List<Medicine> medicines = medicineService.getAllMedicines();
        model.addAttribute("medicines", medicines);
        return "medicines/list-medicines";
    }

    @GetMapping("/addMedicine")
    public String getMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "medicines/addMedicine";
    }

    @PostMapping("/save")
    public String saveMedicine(@ModelAttribute("medicine") Medicine medicine) {
        System.out.println("Saving Medicine: " + medicine.getMedicineName() +
                ", Manufacture Date: " + medicine.getManufactureDate() +
                ", Expiry Date: " + medicine.getExpiryDate());
        medicineService.save(medicine);
        return "redirect:/medicines/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("medicineId") Long id, Model model) {
        Medicine medicine = medicineService.findById(id);
        if (medicine == null) {
            model.addAttribute("error", "Medicine not found");
            return "redirect:/medicines/list";
        }
        model.addAttribute("medicine", medicine);
        return "medicines/addMedicine";
    }

    @GetMapping("/delete")
    public String deleteMedicine(@RequestParam("medicineId") Long id) {
        medicineService.deleteById(id);
        return "redirect:/medicines/list";
    }
}