package com.conciliacion.wsconciliacion.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conciliacion.wsconciliacion.entity.PaymentHeader;
@Repository
public interface PaymentHeaderRepository extends JpaRepository<PaymentHeader, Integer>{	
}
