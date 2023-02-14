package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@Slf4j
public class Libro {

    @Id
    private String isbn;

    private String nombre;

    private LocalDate fechaPublicacion;

    public void imprimir(){
        log.info("Hola, es un libro");
    }

}
