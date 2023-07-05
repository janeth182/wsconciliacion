package com.conciliacion.wsconciliacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conciliacion.wsconciliacion.entity.PaymentDetail;
@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Integer>{

}
