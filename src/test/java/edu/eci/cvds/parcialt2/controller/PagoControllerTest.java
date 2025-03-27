package edu.eci.cvds.parcialt2.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.eci.cvds.parcialt2.controlller.PagoController;
import edu.eci.cvds.parcialt2.model.Pago;
import edu.eci.cvds.parcialt2.service.PagoService;

class PagoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PagoService pagoService;

    @InjectMocks
    private PagoController pagoController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagoController).build();
    }

    @Test
    void testRealizarPago_Exitoso() throws Exception {
        Pago pago = new Pago();
        pago.setUsuarioId("12345");
        pago.setTotal(1000);
        pago.setEstado("APROBADO");

        when(pagoService.procesarPago(any(Pago.class))).thenReturn(pago);

        mockMvc.perform(post("/pagos/realizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pago)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioId").value("12345"))
                .andExpect(jsonPath("$.total").value(1000))
                .andExpect(jsonPath("$.estado").value("APROBADO"));
    }

    @Test
    void testConsultarPagosPorUsuario() throws Exception {
        Pago pago1 = new Pago();
        pago1.setUsuarioId("12345");
        pago1.setTotal(1000);
        pago1.setEstado("APROBADO");

        Pago pago2 = new Pago();
        pago2.setUsuarioId("12345");
        pago2.setTotal(500);
        pago2.setEstado("APROBADO");

        List<Pago> pagos = Arrays.asList(pago1, pago2);

        when(pagoService.consultarPagosPorUsuario("12345")).thenReturn(pagos);

        mockMvc.perform(get("/pagos/usuario/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].total").value(1000))
                .andExpect(jsonPath("$[1].total").value(500));
    }
}