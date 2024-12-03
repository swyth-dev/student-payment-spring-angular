package com.swyth.studentpayment.backend.controller;

import com.swyth.studentpayment.backend.model.Payment;
import com.swyth.studentpayment.backend.model.PaymentStatus;
import com.swyth.studentpayment.backend.model.PaymentType;
import com.swyth.studentpayment.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public Iterable<Payment> getAllPayments() {
        return paymentService.findAllPayments();
    }

    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable UUID id) {
        return paymentService.findPaymentById(id).get();
    }

    @GetMapping(value = "/payments/{id}/file", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable UUID id) throws IOException {
        return paymentService.findPaymentFile(id);
    }

    @GetMapping("/payments/byStatus/{status}")
    public Iterable<Payment> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        return paymentService.findPaymentsByStatus(status);
    }

    @GetMapping("/payments/byType/{type}")
    public Iterable<Payment> getPaymentsByType(@PathVariable PaymentType type) {
        return paymentService.findPaymentsByType(type);
    }

    @PostMapping(value = "/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment createPayment(@RequestBody MultipartFile file, LocalDate date, double amount, PaymentType paymentType, String studentCode) throws IOException {
        return paymentService.savePayment(file, date, amount, paymentType, studentCode);
    }

    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@PathVariable UUID id, @RequestBody PaymentStatus paymentStatus) {
        return paymentService.updatePaymentStatus(id, paymentStatus);
    }

}