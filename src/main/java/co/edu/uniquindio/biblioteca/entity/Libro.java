package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Libro implements Serializable {

    @Id
    private String isbn;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Genero genero;

    @Column(nullable = false)
    private int unidades;

    @Column(nullable = false)
    private LocalDate fechaPublicacion;

    @ManyToMany
    private List<Autor> autor;

}
