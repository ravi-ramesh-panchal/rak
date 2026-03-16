package com.example.feeservice.service;

import com.example.feeservice.api.ReceiptDto;
import com.example.feeservice.domain.Receipt;
import com.example.feeservice.repository.ReceiptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final StudentClient studentClient;

    public ReceiptService(ReceiptRepository receiptRepository, StudentClient studentClient) {
        this.receiptRepository = receiptRepository;
        this.studentClient = studentClient;
    }

    @Transactional
    public ReceiptDto collectFee(ReceiptDto dto) {
        // verify student exists (simple resilience: timeout/propagate not found)
        Mono<Void> check = studentClient.getStudentById(dto.getStudentId())
                .onErrorMap(ex -> new IllegalStateException("Failed to validate student", ex))
                .then();
        check.block();

        Receipt receipt = new Receipt();
        receipt.setReceiptNumber("RCPT-" + UUID.randomUUID());
        receipt.setStudentId(dto.getStudentId());
        receipt.setAmount(dto.getAmount());
        receipt.setPaymentMode(dto.getPaymentMode());
        receipt.setPaymentDate(LocalDateTime.now());
        receipt.setDescription(dto.getDescription());

        Receipt saved = receiptRepository.save(receipt);
        return toDto(saved);
    }

    @Transactional(readOnly = true)
    public ReceiptDto getById(Long id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Receipt not found with id " + id));
        return toDto(receipt);
    }

    @Transactional(readOnly = true)
    public List<ReceiptDto> getByStudentId(String studentId) {
        return receiptRepository.findByStudentId(studentId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReceiptDto> getAll() {
        return receiptRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ReceiptDto toDto(Receipt receipt) {
        ReceiptDto dto = new ReceiptDto();
        dto.setReceiptNumber(receipt.getReceiptNumber());
        dto.setStudentId(receipt.getStudentId());
        dto.setAmount(receipt.getAmount());
        dto.setPaymentMode(receipt.getPaymentMode());
        dto.setPaymentDate(receipt.getPaymentDate());
        dto.setDescription(receipt.getDescription());
        return dto;
    }
}

