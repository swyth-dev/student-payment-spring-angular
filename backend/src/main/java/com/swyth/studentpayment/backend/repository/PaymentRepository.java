package com.swyth.studentpayment.backend.repository;

import com.swyth.studentpayment.backend.model.Payment;
import com.swyth.studentpayment.backend.model.PaymentStatus;
import com.swyth.studentpayment.backend.model.PaymentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, UUID> {

    List<Payment> findByStudentCode(String code);

    List<Payment> findByStatus(PaymentStatus status);

    List<Payment> findByType(PaymentType type);
}
