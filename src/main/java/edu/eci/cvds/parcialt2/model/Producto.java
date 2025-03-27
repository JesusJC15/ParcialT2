package edu.eci.cvds.parcialt2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private String nombre;
    private double precio;
    private int cantidad;

}
