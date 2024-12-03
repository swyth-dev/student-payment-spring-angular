package com.swyth.studentpayment.backend.service;

import com.swyth.studentpayment.backend.model.Payment;
import com.swyth.studentpayment.backend.model.Student;
import com.swyth.studentpayment.backend.repository.PaymentRepository;
import com.swyth.studentpayment.backend.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Iterable<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findByCode(String studentCode) {
        return studentRepository.findByCode(studentCode);
    }

    public Iterable<Student> findStudentByProgramId(String programId) {
        return studentRepository.findByProgramId(programId);
    }
}
