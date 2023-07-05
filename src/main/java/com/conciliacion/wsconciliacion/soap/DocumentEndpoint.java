package com.conciliacion.wsconciliacion.soap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.conciliacion.wsconciliacion.entity.Document;
import com.conciliacion.wsconciliacion.entity.PaymentDetail;
import com.conciliacion.wsconciliacion.entity.PaymentHeader;
import com.conciliacion.wsconciliacion.service.DocumentService;
import com.ibm.wsdl.util.IOUtils;

import pe.conciliacion.documents.GetAllDocumentsByClientDocNumberRequest;
import pe.conciliacion.documents.GetAllDocumentsByClientDocNumberResponse;
import pe.conciliacion.documents.Header;
import pe.conciliacion.documents.ProcessBulkDocumentRequest;
import pe.conciliacion.documents.ProcessBulkDocumentResponse;
import pe.conciliacion.documents.ServiceStatus;
import pe.conciliacion.documents.Detail;
import pe.conciliacion.documents.DocumentPending;
import pe.conciliacion.documents.ExecutePaymentRequest;
import pe.conciliacion.documents.ExecutePaymentResponse;
import pe.conciliacion.documents.PaymentConfirmation;
import java.io.BufferedReader;
import org.apache.commons.codec.binary.StringUtils;
@Endpoint
public class DocumentEndpoint {
	@Autowired
	private DocumentService service;
	
	@PayloadRoot(namespace = "http://conciliacion.pe/documents", localPart = "ProcessBulkDocumentRequest")
	@ResponsePayload
	public ProcessBulkDocumentResponse processBulkDocument (@RequestPayload ProcessBulkDocumentRequest request) {
		ProcessBulkDocumentResponse response = new ProcessBulkDocumentResponse();
		ServiceStatus serviceStatus= new ServiceStatus();
		service.processBulkDocument(request.getTypeDocument(),request.getBase64());			
		serviceStatus.setMessage("Se proceso correctamente la operacion");
		serviceStatus.setStatusCode("OK");
		response.setServiceStatus(serviceStatus);		
		return response;
	}
	
	@PayloadRoot(namespace = "http://conciliacion.pe/documents", localPart = "GetAllDocumentsByClientDocNumberRequest")
	@ResponsePayload
	public GetAllDocumentsByClientDocNumberResponse getAllDocumentsByClientDocNumber (@RequestPayload GetAllDocumentsByClientDocNumberRequest request) {
		GetAllDocumentsByClientDocNumberResponse response = new GetAllDocumentsByClientDocNumberResponse();
		
		Pageable page = PageRequest.of(request.getOffset()-1, request.getLimit());
		List<Document> documents;
		
		documents = service.findByDocumentNumber(request.getClientDocNumber(), page);		
		List<DocumentPending> documentPendingResponse = new ArrayList<>();		
		
		documents.forEach((item)->{
			DocumentPending ob = new DocumentPending();				 
		     BeanUtils.copyProperties(item, ob);		    
		     documentPendingResponse.add(ob);    
		});
		
		response.getDocumentPending().addAll(documentPendingResponse);
		return response;
	}
	
	@PayloadRoot(namespace = "http://conciliacion.pe/documents", localPart = "ExecutePaymentRequest")
	@ResponsePayload
	public ExecutePaymentResponse executePayment(@RequestPayload ExecutePaymentRequest request) {
		ExecutePaymentResponse response = new ExecutePaymentResponse();
				
		PaymentHeader header = new PaymentHeader();
		header.setCustomerRuc(request.getHeader().getCustomerRuc());
		header.setPaymentConcep(request.getHeader().getPaymentConcep());
		header.setServiceCode(request.getHeader().getServiceCode());
		header.setTotalAmount(request.getHeader().getTotalAmount());
		header.setUserEmail(request.getHeader().getUserEmail());
		header.setCurrency(request.getHeader().getCurrency());
		
		List<PaymentDetail> details = new ArrayList<>();				
		request.getDetail().forEach((item)->{
			PaymentDetail detail = new PaymentDetail();
			BeanUtils.copyProperties(item, detail);
			details.add(detail);
		});
		
		PaymentHeader result = service.savePayment(header, details);
		PaymentConfirmation confirmation = new PaymentConfirmation();
		confirmation.setCIP(result.getCipNumber());
		confirmation.setMessage("Se genero Existoso");
		confirmation.setTotal(result.getTotalAmount());
		response.setPaymentConfirmation(confirmation);		
		return response;
	}
}
