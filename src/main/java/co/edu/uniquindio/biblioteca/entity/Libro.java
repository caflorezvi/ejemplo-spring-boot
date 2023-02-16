package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Slf4j
public class Libro {

    @Id
    private String isbn;

    private String nombre;

    private String genero;

    private int unidades;

    @ManyToMany
    private List<Autor> autor;

    private LocalDate fechaPublicacion;

}
