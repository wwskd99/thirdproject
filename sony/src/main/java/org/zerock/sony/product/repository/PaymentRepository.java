package org.zerock.sony.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sony.product.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
