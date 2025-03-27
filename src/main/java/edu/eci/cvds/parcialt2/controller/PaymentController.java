package edu.eci.cvds.parcialt2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.parcialt2.dto.PaymentDTO;
import edu.eci.cvds.parcialt2.exception.PaymentException;
import edu.eci.cvds.parcialt2.model.Payment;
import edu.eci.cvds.parcialt2.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public ResponseEntity<Payment> makePay(@RequestBody PaymentDTO paymentDTO) throws PaymentException{
        Payment newPayment = paymentService.processPayment(paymentDTO);
        return ResponseEntity.ok(newPayment);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> consultarPagos(@PathVariable String userId){
        List<Payment> payments = paymentService.getPaymentsByUserId(userId);
        return ResponseEntity.ok(payments);
    }
}