package com.hexaware.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hexaware.enums.PaymentMethod;
import com.hexaware.enums.PaymentStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "payments")
public class Payment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
    private int paymentId;
	
//	 @OneToOne
//	 @JoinColumn(name = "order_id", nullable = false, unique = true)
//	 private Order order;

    @NotNull(message = "Payment amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Payment status cannot be null")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status; 

    @NotNull(message = "Payment method cannot be null")
    @Enumerated(EnumType.STRING)
    private PaymentMethod method; 

    @NotNull(message = "Payment date and time cannot be null")
    @Column(name = "payment_date_time")
    private LocalDateTime paymentDateTime;

}
