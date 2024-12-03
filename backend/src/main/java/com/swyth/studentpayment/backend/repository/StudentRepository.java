package com.swyth.studentpayment.backend.repository;

import com.swyth.studentpayment.backend.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<Student, UUID> {

    Student findByCode(String code);

    List<Student> findByProgramId(String programId);

}
