package edu.eci.cvds.parcialt2.controlller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.parcialt2.model.Pago;
import edu.eci.cvds.parcialt2.service.PagoService;

@RestController
@RequestMapping("/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/realizar")
    public ResponseEntity<?> realizarPago(@RequestBody Pago pago) {
        try {
            Pago nuevoPago = pagoService.procesarPago(pago);
            return ResponseEntity.ok(nuevoPago);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Pago>> consultarPagos(@PathVariable String usuarioId){
        List<Pago> pagos = pagoService.consultarPagosPorUsuario(usuarioId);
        return ResponseEntity.ok(pagos);
    }
}
