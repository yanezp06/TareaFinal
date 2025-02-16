package com.backend.tarea;

import com.backend.tarea.model.Cliente;
import com.backend.tarea.repository.ClienteRepository;
import com.backend.tarea.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Ana Gómez");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.guardarCliente(cliente);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo("Ana Gómez");
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testObtenerClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNombre("Ana Gómez");
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteService.obtenerClientePorId(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("Ana Gómez");
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    public void testListarClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Ana Gómez");
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Carlos Pérez");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> resultado = clienteService.listarClientes();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Ana Gómez");
        assertThat(resultado.get(1).getNombre()).isEqualTo("Carlos Pérez");
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testEliminarCliente() {
        Long idCliente = 1L;

        doNothing().when(clienteRepository).deleteById(idCliente);

        clienteService.eliminarCliente(idCliente);

        verify(clienteRepository, times(1)).deleteById(idCliente);
    }
}