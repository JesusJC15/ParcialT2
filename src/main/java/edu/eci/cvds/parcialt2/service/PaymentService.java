package edu.eci.cvds.parcialt2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.cvds.parcialt2.dto.PaymentDTO;
import edu.eci.cvds.parcialt2.exception.PaymentException;
import edu.eci.cvds.parcialt2.model.Payment;
import edu.eci.cvds.parcialt2.model.Product;
import edu.eci.cvds.parcialt2.repository.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    
    /**
     * Process a payment
     * @param payment
     * @return
     * @throws PaymentException 
    */
    public Payment processPayment(PaymentDTO paymentDTO) throws PaymentException {
        Payment payment = new Payment(paymentDTO.getId(), paymentDTO.getUserId(), paymentDTO.getProducts(), paymentDTO.getTotal(), paymentDTO.getDate(), paymentDTO.getState());
        double total = payment.getProducts().stream().mapToDouble(Product::getPrice).sum();
        if(total != payment.getTotal()){
            payment.setState("REJECTED");
            throw new PaymentException(PaymentException.INVALID_TOTAL);
        }
        payment.setState("ACCEPTED");

        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByUserId(String userId) {
        return paymentRepository.findByUserId(userId);
    }
}
