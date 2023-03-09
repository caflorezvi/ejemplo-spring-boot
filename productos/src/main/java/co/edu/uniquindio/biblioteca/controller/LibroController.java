package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.LibroDTO;
import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.entity.Libro;
import co.edu.uniquindio.biblioteca.servicios.LibroServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libro")
@AllArgsConstructor
public class LibroController {

    private final LibroServicio libroServicio;

    @PostMapping
    public ResponseEntity<Respuesta<Libro>> save(@RequestBody LibroDTO libroDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Respuesta<>("Libro creado correctamente", libroServicio.save(libroDTO)) );
    }

    @GetMapping
    public ResponseEntity<Respuesta<List<Libro>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", libroServicio.findAll()) );
    }

    @GetMapping("/{isbnLibro}")
    public ResponseEntity<Respuesta<Libro>> findAll(@PathVariable String isbnLibro){
        return ResponseEntity.status(HttpStatus.OK).body( new Respuesta<>("", libroServicio.findById(isbnLibro)) );
    }

}
