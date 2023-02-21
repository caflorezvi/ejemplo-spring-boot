package co.edu.uniquindio.biblioteca.dto;

import co.edu.uniquindio.biblioteca.entity.Genero;
import java.time.LocalDate;
import java.util.List;

public record LibroDTO(String isbn, String nombre, Genero genero, int unidades, LocalDate fechaPublicacion, List<Long> idAutores){

}
