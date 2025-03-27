package edu.eci.cvds.parcialt2.model;

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
@Document(collection = "pagos")
public class Pago {

    @Id
    private String id;
    private String usuarioId;
    private List<Producto> productos;
    private double total;
    private String fecha;
    private String estado;
}
