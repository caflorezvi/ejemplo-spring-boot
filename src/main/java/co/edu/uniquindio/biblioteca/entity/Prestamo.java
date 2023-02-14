package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    @ManyToOne
    private Cliente codigoCliente;


    @ManyToMany
    private List<Libro> libros;

}
