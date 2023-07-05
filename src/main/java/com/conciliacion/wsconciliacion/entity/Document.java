package com.conciliacion.wsconciliacion.entity;

import java.util.Date;

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
@Table(name="pending_documents")
@EntityListeners(AuditingEntityListener.class)
public class Document {
		@Id	
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		@Column(name="cu")
	 	private String cu;
		@Column(name="doc_type")
	    private String docType;
		@Column(name="zone")
	    private String zone;
		@Column(name="series")
	    private String series;
		@Column(name="sequence")
	    private String sequence;
		@Column(name="description")
	    private String description;
		@Column(name="client_doc_type")
	    private String clientDocType;
		@Column(name="client_doc_number")
	    private String clientDocNumber;
		@Column(name="company_name")
	    private String companyName;
		@Column(name="issue_date")
	    private String issueDate;
		@Column(name="due_date")
	    private String dueDate;
		@Column(name="reason")
	    private String reason;
		@Column(name="reference_number")
	    private String referenceNumber;
		@Column(name="affectation_instruction")
	    private String affectationInstruction;
		@Column(name="application_instruction")
	    private String applicationInstruction;
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
		@Column(name="process_date")
	    private String process_date;
		@Column(name="process_user")
	    private String processUser;
		@Column(name="payment_type")
	    private String paymentType;
		@Column(name="additional_data_one")
	    private String additionalDataOne;
		@Column(name="additional_data_two")
	    private String additionalDataTwo;
		@Column(name="additional_data_three")
	    private String additionalDataThree;
		@Column(name="additional_data_four")
	    private String additionalDataFour;
		@Column(name="additional_data_five")
	    private String additionalDataFive;
		@Column(name="additional_data_six")
	    private String additionalDataSix;
	    
}
