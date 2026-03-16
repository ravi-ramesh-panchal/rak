package com.example.feeservice.repository;

import com.example.feeservice.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findByStudentId(String studentId);
}

