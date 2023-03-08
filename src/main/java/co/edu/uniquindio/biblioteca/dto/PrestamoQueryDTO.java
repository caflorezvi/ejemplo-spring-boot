package co.edu.uniquindio.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PrestamoQueryDTO {

    @EqualsAndHashCode.Include
    private long prestamoID;
    long clienteID;
    private String isbnLibro;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaDevolucion;

}
