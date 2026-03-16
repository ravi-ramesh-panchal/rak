package com.example.feeservice.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Fee collection receipt")
public class ReceiptDto {

    @Schema(description = "Generated receipt number", example = "RCPT-20240314-001")
    private String receiptNumber;

    @Schema(description = "Student business identifier", example = "STU-001")
    private String studentId;

    @Schema(description = "Amount paid", example = "1500.00")
    private BigDecimal amount;

    @Schema(description = "Payment mode", example = "CASH")
    private String paymentMode;

    @Schema(description = "Date and time of payment")
    private LocalDateTime paymentDate;

    @Schema(description = "Optional description", example = "Tuition fee for March")
    private String description;

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

