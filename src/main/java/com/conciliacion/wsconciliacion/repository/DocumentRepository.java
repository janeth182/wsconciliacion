package com.conciliacion.wsconciliacion.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conciliacion.wsconciliacion.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>{
	List<Document>findByClientDocNumberContaining(String clientDocNumber, Pageable page);
}
