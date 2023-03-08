package co.edu.uniquindio.biblioteca.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PrestamoGetDTO(long prestamoID, long clienteID, List<String> isbnLibros, LocalDateTime fechaCreacion, LocalDateTime fechaDevolucion) {
}
