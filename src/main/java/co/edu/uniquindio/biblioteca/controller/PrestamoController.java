package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.PrestamoGetDTO;
import co.edu.uniquindio.biblioteca.dto.PrestamoPostDTO;
import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.servicios.PrestamoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prestamo")
@AllArgsConstructor
public class PrestamoController {

    private final PrestamoServicio prestamoServicio;

    @PostMapping
    public ResponseEntity<Respuesta<Long>> save(@RequestBody PrestamoPostDTO prestamoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>("Se ha registrado el préstamo", prestamoServicio.save(prestamoDTO)));
    }

    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<Respuesta<List<PrestamoGetDTO>>> findByCodigoCliente(@PathVariable long codigoCliente){
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", prestamoServicio.findByCodigoCliente(codigoCliente)));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<Respuesta<List<PrestamoGetDTO>>> findByDate(@PathVariable LocalDate fecha){
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", prestamoServicio.findByDate(fecha)));
    }

    @GetMapping("/libro/{isbn}")
    public ResponseEntity<Respuesta<Long>> lendingCount(@PathVariable String isbn){
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", prestamoServicio.lendingCount(isbn)));
    }

    @GetMapping("/{codigoPrestamo}")
    public ResponseEntity<Respuesta<PrestamoGetDTO>> findById(@PathVariable long codigoPrestamo){
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", prestamoServicio.findById(codigoPrestamo)));
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<PrestamoGetDTO>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", prestamoServicio.findAll()));
    }

    @PutMapping("/{codigoPrestamo}")
    public ResponseEntity<Respuesta<Long>> update(@PathVariable long codigoPrestamo, @RequestBody PrestamoPostDTO prestamoPostDTO){
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Se ha actualizado el préstamo", prestamoServicio.update(codigoPrestamo, prestamoPostDTO)));
    }

}
