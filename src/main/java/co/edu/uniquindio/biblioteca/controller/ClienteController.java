package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.ClienteGet;
import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteController {

    private final ClienteServicio clienteServicio;

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente){
        return clienteServicio.save(cliente);
    }

    @GetMapping("/{idCliente}")
    public ClienteGet findById(@PathVariable long idCliente){
        return clienteServicio.findById(idCliente);
    }

    @GetMapping
    public List<ClienteGet> findAll(){
        return clienteServicio.findAll();
    }

    @DeleteMapping("/{idCliente}")
    public String delete(@PathVariable long idCliente){
        clienteServicio.delete(idCliente);
        return "Se eliminó";
    }


    @PutMapping("/{idCliente}")
    public Cliente update(@PathVariable long idCliente, @RequestBody Cliente cliente){
        return clienteServicio.update(idCliente, cliente);
    }

}
