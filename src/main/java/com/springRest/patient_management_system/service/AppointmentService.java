package com.springRest.patient_management_system.service;

import com.springRest.patient_management_system.entity.Appointment;
import com.springRest.patient_management_system.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void save(Appointment appointment) {
        // Validate appointment
        if (!isValidAppointment(appointment)) {
            throw new IllegalArgumentException("Selected date or time slot is not available for the doctor.");
        }
        appointmentRepository.save(appointment);
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    private boolean isValidAppointment(Appointment appointment) {
        String availableDates = appointment.getDoctor().getAvailableDates();
        String availableSlots = appointment.getDoctor().getAvailableSlots();
        if (availableDates == null || availableSlots == null) {
            return false;
        }

        // Check if the appointment date is in available dates
        List<String> dates = Arrays.asList(availableDates.split(","));
        String appointmentDateStr = appointment.getAppointmentDate().toString();
        if (!dates.contains(appointmentDateStr)) {
            return false;
        }

        // Check if the time slot is in available slots
        List<String> slots = Arrays.asList(availableSlots.split(","));
        return slots.contains(appointment.getTimeSlot());
    }
}