package com.swyth.studentpayment.backend;

import com.swyth.studentpayment.backend.model.Payment;
import com.swyth.studentpayment.backend.model.PaymentStatus;
import com.swyth.studentpayment.backend.model.PaymentType;
import com.swyth.studentpayment.backend.model.Student;
import com.swyth.studentpayment.backend.repository.PaymentRepository;
import com.swyth.studentpayment.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Autowired
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){
        return args -> {
                studentRepository.save(Student.builder().id(UUID.randomUUID())
                    .code("112233").firstName("Mohamed").programId("GLSID").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID())
                    .code("112244").firstName("Imane").programId("GLSID").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID())
                    .code("112255").firstName("Aymane").programId("BDCC").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID())
                    .code("112266").firstName("Lobna").programId("BDCC").build());

            PaymentType[] paymentTypes = PaymentType.values();
            Random random=new Random();
            studentRepository.findAll().forEach(st->{
                for (int i = 0; i <10 ; i++) {
                    int index = random.nextInt(paymentTypes.length);
                    Payment payment = Payment.builder()
                            .date(LocalDate.now())
                            .amount(1000+(int)(Math.random()*20000))
                            .type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .student(st)
                            .build();
                    paymentRepository.save(payment);
                }
            });
        };
    }
}
