package com.system.attendance.controller;

import com.system.attendance.model.Attendance;
import com.system.attendance.repo.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular frontend to access this API
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Get all attendances
    @GetMapping
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    // Get an attendance by ID
    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        Optional<Attendance> attendance = attendanceRepository.findById(id);
        return attendance.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new attendance
    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        Attendance createdAttendance = attendanceRepository.save(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAttendance);
    }

    // Update an existing attendance
    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendanceDetails) {
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);
        if (optionalAttendance.isPresent()) {
            Attendance existingAttendance = optionalAttendance.get();
            existingAttendance.setDate(attendanceDetails.getDate());
            existingAttendance.setClockIn(attendanceDetails.getClockIn());
            existingAttendance.setClockOut(attendanceDetails.getClockOut());
            Attendance updatedAttendance = attendanceRepository.save(existingAttendance);
            return ResponseEntity.ok(updatedAttendance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an attendance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}