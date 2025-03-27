package edu.eci.cvds.parcialt2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.parcialt2.model.Pago;
import edu.eci.cvds.parcialt2.repository.PagoRepository;

@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    /**
     * Procesa un pago
     * @param pago
     * @return
     */
    public Pago procesarPago(Pago pago){
        double totalCalculado = pago.getProductos().stream()
            .mapToDouble(p -> p.getPrecio() * p.getCantidad())
            .sum();

        if(totalCalculado != pago.getTotal()){
            throw new IllegalArgumentException("El total de la transacción no coincide con la suma de los productos.");
        }

        pago.setEstado("APROBADO");
        return pagoRepository.save(pago);
    }

    public List<Pago> consultarPagosPorUsuario(String usuarioId){
        return pagoRepository.findByUsuarioId(usuarioId);
    }
}
