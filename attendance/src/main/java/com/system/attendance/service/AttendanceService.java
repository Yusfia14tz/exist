package com.system.attendance.service;

import com.system.attendance.model.Attendance;
import com.system.attendance.repo.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // Method to fetch all attendances
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    // Method to fetch a specific attendance by ID
    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    // Method to create a new attendance
    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    // Method to update an existing attendance
    public Optional<Attendance> updateAttendance(Long id, Attendance attendanceDetails) {
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);
        if (optionalAttendance.isPresent()) {
            Attendance existingAttendance = optionalAttendance.get();
            existingAttendance.setDate(attendanceDetails.getDate());
            existingAttendance.setClockIn(attendanceDetails.getClockIn());
            existingAttendance.setClockOut(attendanceDetails.getClockOut());
            return Optional.of(attendanceRepository.save(existingAttendance));
        } else {
            return Optional.empty();
        }
    }

    // Method to delete an attendance
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

    public Attendance getAttendanceById(long attendanceId) {
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(attendanceId);
        return optionalAttendance.get();
    }
    public Attendance addAttendance(Attendance attendance) {

        return attendanceRepository.save(attendance);
    }
    public Attendance updateAttendance(Attendance attendance) {
        Attendance existAttendance = attendanceRepository.findById(attendance.getId()).get();
        existAttendance.setDate(attendance.getDate());
        existAttendance.setClockin(attendance.getClockIn());
        existAttendance.setClockout(attendance.getClockOut());
        Attendance updateAttendance = attendanceRepository.save(existAttendance);
        return updateAttendance;
    }
}
