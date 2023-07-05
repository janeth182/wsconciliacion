package com.conciliacion.wsconciliacion.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="payment_effective_header")
@EntityListeners(AuditingEntityListener.class)
public class PaymentHeader {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_payment_order;
	
	@Column(name="customer_ruc")
 	private String customerRuc;
	
	@Column(name="cip_number")
	private String cipNumber;
	
	@Column(name="cip_status")
	private Integer cipStatus;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="total_amount")
	private double totalAmount;
	
	@Column(name="payment_concept")
	private String paymentConcep;
	
	@Column(name="service_code")
	private String serviceCode;
	
	@Column(name="commerce_email")
	private String commerceEmail;
	
	@Column(name="user_first_name")
	private String userFirstName;
	
	@Column(name="user_last_name")
	private String userLastName;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="cip_payment_date")
	private Date cipPaymentDate;
	
	@Column(name="cip_expiration_date")
	private Date cipExpirationDate;
	
	@Column(name="reconciliation_date")
	private Date reconciliationDate;
	
	@Column(name="settlement_date")
	private Date settlementDate;
		
}
