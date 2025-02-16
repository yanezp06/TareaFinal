package com.backend.tarea;

import com.backend.tarea.controller.UsuarioController;
import com.backend.tarea.model.Usuario;
import com.backend.tarea.service.UsuarioService;
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

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    public void setUp() {
        // Usamos el constructor existente y configuramos el ID manualmente
        usuario1 = new Usuario("admin", "admin@example.com", "password123", "ADMIN");
        usuario1.setIdUsuario(1L);

        usuario2 = new Usuario("user", "user@example.com", "password456", "USER");
        usuario2.setIdUsuario(2L);
    }

    @Test
    public void testListarUsuarios() throws Exception {
        when(usuarioService.listarUsuarios()).thenReturn(Arrays.asList(usuario1, usuario2));

        mockMvc.perform(get("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("admin"))
                .andExpect(jsonPath("$[1].nombre").value("user"));
    }

    @Test
    public void testGuardarUsuario() throws Exception {
        when(usuarioService.guardarUsuario(any(Usuario.class))).thenReturn(usuario1);

        String usuarioJson = """
                {
                    "nombre": "admin",
                    "email": "admin@example.com",
                    "password": "password123",
                    "rol": "ADMIN"
                }
                """;

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("admin"));
    }

    @Test
    public void testObtenerUsuarioPorId() throws Exception {
        when(usuarioService.obtenerUsuarioPorId(1L)).thenReturn(Optional.of(usuario1));

        mockMvc.perform(get("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("admin"));
    }

    @Test
    public void testEliminarUsuario() throws Exception {
        mockMvc.perform(delete("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).eliminarUsuario(1L);
    }
}
