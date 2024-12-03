package com.swyth.studentpayment.backend.controller;

import com.swyth.studentpayment.backend.model.Payment;
import com.swyth.studentpayment.backend.model.Student;
import com.swyth.studentpayment.backend.service.PaymentService;
import com.swyth.studentpayment.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/students")
    public Iterable<Student> getAllStudents() {
        return studentService.findAllStudents();
    }


    @GetMapping("/students/{studentCode}")
    public Student getStudent(@PathVariable String studentCode) {
        return studentService.findByCode(studentCode);
    }

    @GetMapping("/students/{studentCode}/payments")
    public Iterable<Payment> getAllPayments(@PathVariable String studentCode) {
        return paymentService.findAllByStudentCode(studentCode);
    }

    @GetMapping("/students/byProgramId/{programId}")
    public Iterable<Student> getStudentByProgramId(@PathVariable String programId) {
        return studentService.findStudentByProgramId(programId);
    }
}
