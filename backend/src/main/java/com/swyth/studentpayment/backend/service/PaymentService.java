package com.swyth.studentpayment.backend.service;

import com.swyth.studentpayment.backend.model.Payment;
import com.swyth.studentpayment.backend.model.PaymentStatus;
import com.swyth.studentpayment.backend.model.PaymentType;
import com.swyth.studentpayment.backend.model.Student;
import com.swyth.studentpayment.backend.repository.PaymentRepository;
import com.swyth.studentpayment.backend.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Data
@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private StudentRepository studentRepository;

    public Iterable<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    ;

    public Optional<Payment> findPaymentById(UUID id) {
        return paymentRepository.findById(id);
    }

    public Iterable<Payment> findAllByStudentCode(String studentCode) {
        return paymentRepository.findByStudentCode(studentCode);
    }

    public Iterable<Payment> findPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    public Iterable<Payment> findPaymentsByType(PaymentType type) {
        return paymentRepository.findByType(type);
    }

    public byte[] findPaymentFile(UUID paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();

        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }

    public Payment updatePaymentStatus(UUID id, PaymentStatus paymentStatus) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            payment.get().setStatus(paymentStatus);
        } else {
            throw new RuntimeException("Payment not found");
        }
        return paymentRepository.save(payment.get());
    }

    public Payment savePayment(MultipartFile file, LocalDate date, double amount, PaymentType paymentType, String studentCode) throws IOException {
        // We will store file on the machine for demo purpose
        Path folderPath = Paths.get(System.getProperty("user.home"), "student-data", "data");
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        String fileName = UUID.randomUUID().toString() + ".pdf";
        Path filePath = Paths.get(System.getProperty("user.home"), "student-data", "data", fileName);
        Files.copy(file.getInputStream(), filePath);

        Student student = studentRepository.findByCode(studentCode);

        Payment newPayment = Payment.builder()
                .date(date)
                .amount(amount)
                .type(paymentType)
                .status(PaymentStatus.CREATED)
                .file(filePath.toUri().toString())
                .student(student)
                .build();

        return paymentRepository.save(newPayment);
    }
}
