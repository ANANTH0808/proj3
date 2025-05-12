package com.springRest.patient_management_system.repository;

import com.springRest.patient_management_system.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}