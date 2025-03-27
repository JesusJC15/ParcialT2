package edu.eci.cvds.parcialt2.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    private String userId;
    private List<Product> products;
    private double total;
    private LocalDate date;
    private String state;
}
