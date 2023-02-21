package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.ClienteGet;
import co.edu.uniquindio.biblioteca.dto.ClientePost;
import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClienteController {

    private final ClienteServicio clienteServicio;

    @PostMapping
    public ResponseEntity<Respuesta<ClienteGet>> save(@RequestBody ClientePost cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Cliente creado correctamente", clienteServicio.save(cliente)) );
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Respuesta<ClienteGet>> findById(@PathVariable long idCliente){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", clienteServicio.findById(idCliente)) );
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<ClienteGet>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", clienteServicio.findAll()) );

    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Respuesta<String>> delete(@PathVariable long idCliente){
        clienteServicio.delete(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("Se eliminó correctamente") );
    }


    @PutMapping("/{idCliente}")
    public ResponseEntity<Respuesta<ClienteGet>> update(@PathVariable long idCliente, @RequestBody ClientePost cliente){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("El cliente se modificó correctamente", clienteServicio.update(idCliente, cliente)) );
    }

}
