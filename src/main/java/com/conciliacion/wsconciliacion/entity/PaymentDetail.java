package com.conciliacion.wsconciliacion.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="payment_effective_detail")
@EntityListeners(AuditingEntityListener.class)
public class PaymentDetail {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_detail;
	@Column(name="cu")
 	private String cu;
	@Column(name="doc_type")
    private String docType;	
	@Column(name="description")
    private String description;	
	@Column(name="issue_date")
    private String issueDate;	
	@Column(name="reference_number")
    private String referenceNumber;	
	@Column(name="currency")
    private String currency;
	@Column(name="sub_total")
    private double subTotal;
	@Column(name="igv")
    private double igv;
	@Column(name="delay")
    private String delay;
	@Column(name="total")
    private double total;
	@Column(name="id_payment_order")
	private Integer idPaymentOrder;
}
