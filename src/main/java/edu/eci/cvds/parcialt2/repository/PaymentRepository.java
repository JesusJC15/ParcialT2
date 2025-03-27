package edu.eci.cvds.parcialt2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.cvds.parcialt2.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByUserId(String userId);
}
