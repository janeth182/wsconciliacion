package com.conciliacion.wsconciliacion.service;

import org.springframework.data.domain.Pageable;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conciliacion.wsconciliacion.entity.Document;
import com.conciliacion.wsconciliacion.entity.PaymentDetail;
import com.conciliacion.wsconciliacion.entity.PaymentHeader;
import com.conciliacion.wsconciliacion.repository.DocumentRepository;
import com.conciliacion.wsconciliacion.repository.PaymentDetailRepository;
import com.conciliacion.wsconciliacion.repository.PaymentHeaderRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentService {
	@Autowired
	private DocumentRepository repository;
	@Autowired
	private PaymentHeaderRepository headerRepository;
	@Autowired
	private PaymentDetailRepository detailRepository;
	
	@Transactional(readOnly=true)
	public List<Document> findByDocumentNumber(String documentNumber, Pageable page){
		try {
			return repository.findByClientDocNumberContaining(documentNumber, page);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	@Transactional
	public PaymentHeader savePayment(PaymentHeader header, List<PaymentDetail> details) {
		try {				
			String cipNumber = getRandomNumber();
			Integer cipStatus = 23; 
			header.setCipNumber(cipNumber);
			header.setCipStatus(cipStatus);
			PaymentHeader nuevo = headerRepository.save(header);
			for(PaymentDetail detail : details) {	
				detail.setIdPaymentOrder(nuevo.getId_payment_order());
				detailRepository.save(detail);
			}
			return nuevo;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	@Transactional
	public Document processBulkDocument(String typeDocument, String base64) {
		try {				
			byte[] decodedFile = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));	
			String data = new String(decodedFile);					
			data.lines().forEach((item)->{
					if(item.length() > 53) {												
						Document nuevo = new Document();
						nuevo.setCu(item.substring(0,19));
						nuevo.setDocType(item.substring(20,21));			
						nuevo.setZone(item.substring(22,26));	
						nuevo.setSeries(item.substring(27,34));
						nuevo.setSequence(item.substring(35,54));
						nuevo.setDescription(item.substring(55,154));
						nuevo.setClientDocType(item.substring(155,155));
						nuevo.setClientDocNumber(item.substring(156,175));
						nuevo.setCompanyName(item.substring(176,315));
						nuevo.setDueDate(item.substring(316,323));
						nuevo.setIssueDate(item.substring(324,331));
						nuevo.setReason(item.substring(332,371));
						nuevo.setReferenceNumber(item.substring(372,387));
						nuevo.setAffectationInstruction(item.substring(388, 389));
						nuevo.setApplicationInstruction(item.substring(390, 439));
						nuevo.setCurrency(item.substring(440,442));
						nuevo.setSubTotal(Double.parseDouble(item.substring(443,462)));
						nuevo.setIgv(Double.parseDouble(item.substring(463,482)));
						nuevo.setDelay(item.substring(483,502));
						nuevo.setTotal(Double.parseDouble(item.substring(503,522)));
						nuevo.setProcess_date(item.substring(543, 556));
						nuevo.setProcessUser(item.substring(557, 571));
						nuevo.setAdditionalDataOne(item.substring(574, 593));
						repository.save(nuevo);	
					}
			}); 
							
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		
	}		
		
	private String getRandomNumber() {
		int cipNumber = (int) Math.floor(Math.random() * (900000 - 100000  + 1)) + 100000 ; 
	    return "000"+ Integer.toString(cipNumber);
	}
}
