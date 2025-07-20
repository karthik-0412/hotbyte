package com.hexaware.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hexaware.enums.PaymentMethod;
import com.hexaware.enums.PaymentStatus;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDTO {
	private int paymentId;

    private BigDecimal amount;

    private PaymentStatus status;

    private PaymentMethod method;

    private LocalDateTime paymentDateTime;
    private int orderId;
}
