package edu.eci.cvds.parcialt2.dto;

import java.time.LocalDate;
import java.util.List;

import edu.eci.cvds.parcialt2.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String id;
    private String userId;
    private List<Product> products;
    private double total;
    private LocalDate date;
    private String state;
}
