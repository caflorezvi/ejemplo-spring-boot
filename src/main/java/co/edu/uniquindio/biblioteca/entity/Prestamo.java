package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @ManyToOne
    private Cliente codigoCliente;

    private LocalDateTime fechaPrestamo;

    private LocalDateTime fechaDevolucion;

    @ManyToMany
    private List<Libro> libros;

}
