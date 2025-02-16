package com.backend.tarea;

import com.backend.tarea.controller.ClienteController;
import com.backend.tarea.model.Cliente;
import com.backend.tarea.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    public void setUp() {
        // Usamos el constructor existente y configuramos el ID manualmente
        cliente1 = new Cliente("Juan", "Perez", "12345678", "juan.perez@example.com", "123456789");
        cliente1.setIdCliente(1L);

        cliente2 = new Cliente("Maria", "Lopez", "87654321", "maria.lopez@example.com", "987654321");
        cliente2.setIdCliente(2L);
    }

    @Test
    public void testListarClientes() throws Exception {
        when(clienteService.listarClientes()).thenReturn(Arrays.asList(cliente1, cliente2));

        mockMvc.perform(get("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[1].nombre").value("Maria"));
    }

    @Test
    public void testGuardarCliente() throws Exception {
        when(clienteService.guardarCliente(any(Cliente.class))).thenReturn(cliente1);

        String clienteJson = """
                {
                    "nombre": "Juan",
                    "apellido": "Perez",
                    "documento": "12345678",
                    "email": "juan.perez@example.com",
                    "telefono": "123456789"
                }
                """;

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    public void testObtenerClientePorId() throws Exception {
        when(clienteService.obtenerClientePorId(1L)).thenReturn(Optional.of(cliente1));

        mockMvc.perform(get("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    public void testEliminarCliente() throws Exception {
        mockMvc.perform(delete("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clienteService, times(1)).eliminarCliente(1L);
    }
}
