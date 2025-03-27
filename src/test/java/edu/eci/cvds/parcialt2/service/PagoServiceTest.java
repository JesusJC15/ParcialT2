package edu.eci.cvds.parcialt2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.parcialt2.model.Pago;
import edu.eci.cvds.parcialt2.model.Producto;
import edu.eci.cvds.parcialt2.repository.PagoRepository;

class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcesarPago_Exitoso() {
        // Crear productos
        Producto producto1 = new Producto();
        producto1.setNombre("Laptop");
        producto1.setPrecio(1000);
        producto1.setCantidad(1);

        Producto producto2 = new Producto();
        producto2.setNombre("Mouse");
        producto2.setPrecio(50);
        producto2.setCantidad(2);

        // Crear pago con total correcto
        Pago pago = new Pago();
        pago.setUsuarioId("12345");
        pago.setProductos(Arrays.asList(producto1, producto2));
        pago.setTotal(1100);
        pago.setFecha("26-03-2025");

        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        // Ejecutar el método
        Pago resultado = pagoService.procesarPago(pago);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals("APROBADO", resultado.getEstado());
        verify(pagoRepository, times(1)).save(any(Pago.class));
    }

    @Test
    void testProcesarPago_TotalIncorrecto() {
        // Crear productos
        Producto producto1 = new Producto();
        producto1.setNombre("Laptop");
        producto1.setPrecio(1000);
        producto1.setCantidad(1);

        Producto producto2 = new Producto();
        producto2.setNombre("Mouse");
        producto2.setPrecio(50);
        producto2.setCantidad(2);

        // Crear pago con total incorrecto
        Pago pago = new Pago();
        pago.setUsuarioId("12345");
        pago.setProductos(Arrays.asList(producto1, producto2));
        pago.setTotal(1000); // Incorrecto, debería ser 1100
        pago.setFecha("26-03-2025");

        // Verifica que lanza una excepción si el total es incorrecto
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            pagoService.procesarPago(pago);
        });

        assertEquals("El total de la transacción no coincide con la suma de los productos.", thrown.getMessage());
        verify(pagoRepository, never()).save(any(Pago.class)); // No debería guardarse
    }

    @Test
    void testConsultarPagosPorUsuario() {
        // Crear pagos simulados
        Pago pago1 = new Pago();
        pago1.setId("1");
        pago1.setUsuarioId("12345");
        pago1.setEstado("APROBADO");

        Pago pago2 = new Pago();
        pago2.setId("2");
        pago2.setUsuarioId("12345");
        pago2.setEstado("APROBADO");

        List<Pago> pagosMock = Arrays.asList(pago1, pago2);

        when(pagoRepository.findByUsuarioId("12345")).thenReturn(pagosMock);

        // Ejecutar método
        List<Pago> resultado = pagoService.consultarPagosPorUsuario("12345");

        // Verificaciones
        assertEquals(2, resultado.size());
        verify(pagoRepository, times(1)).findByUsuarioId("12345");
    }
}
