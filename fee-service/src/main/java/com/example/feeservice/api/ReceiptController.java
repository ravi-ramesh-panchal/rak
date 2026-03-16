package com.example.feeservice.api;

import com.example.feeservice.service.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@Tag(name = "Receipts", description = "APIs for collecting fees and viewing receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    @Operation(summary = "Collect fee and generate receipt")
    public ResponseEntity<ReceiptDto> collectFee(@RequestBody ReceiptDto dto) {
        ReceiptDto created = receiptService.collectFee(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get receipt by internal id")
    public ResponseEntity<ReceiptDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(receiptService.getById(id));
    }

    @GetMapping
    @Operation(summary = "List receipts, optionally filter by studentId")
    public ResponseEntity<List<ReceiptDto>> list(@RequestParam(required = false) String studentId) {
        if (studentId != null) {
            return ResponseEntity.ok(receiptService.getByStudentId(studentId));
        }
        return ResponseEntity.ok(receiptService.getAll());
    }
}

