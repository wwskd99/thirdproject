package org.zerock.sony.product.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.zerock.sony.product.dto.PaymentDTO;
import org.zerock.sony.product.entity.Payment;
import org.zerock.sony.product.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService{
	private final PaymentRepository repository;
	
	@Override
	public void insert(PaymentDTO dto) {
		Map<String, Object> entityMap = dtoToEntity(dto);
		Payment payment = (Payment) entityMap.get("payment");
		repository.save(payment);		
	}

}
