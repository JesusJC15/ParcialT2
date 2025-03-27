package edu.eci.cvds.parcialt2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.parcialt2.model.Pago;

@Repository
public interface PagoRepository extends MongoRepository<Pago, String> {
    List<Pago> findByUsuarioId(String usuarioId);

}
