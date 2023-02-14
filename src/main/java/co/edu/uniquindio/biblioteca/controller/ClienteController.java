package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteController {

    private final ClienteServicio clienteServicio;

    @GetMapping("/{idCliente}")
    public Cliente findById(@PathVariable long idCliente){
        return clienteServicio.findById(idCliente);
    }


}
