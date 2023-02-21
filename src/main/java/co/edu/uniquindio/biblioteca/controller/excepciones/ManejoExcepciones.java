package co.edu.uniquindio.biblioteca.controller.excepciones;

import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.servicios.excepciones.AutorNoEncontradoException;
import co.edu.uniquindio.biblioteca.servicios.excepciones.ClienteNoEncontradoException;
import co.edu.uniquindio.biblioteca.servicios.excepciones.LibroNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejoExcepciones {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Respuesta<String>> capturarException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new Respuesta<>(e.getMessage()) );
    }

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<Respuesta<String>> capturarClienteNoEncontradoException(ClienteNoEncontradoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new Respuesta<>(e.getMessage()) );
    }

    @ExceptionHandler(LibroNoEncontradoException.class)
    public ResponseEntity<Respuesta<String>> capturarLibroNoEncontradoException(LibroNoEncontradoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new Respuesta<>(e.getMessage()) );
    }

    @ExceptionHandler(AutorNoEncontradoException.class)
    public ResponseEntity<Respuesta<String>> capturarAutorNoEncontradoException(AutorNoEncontradoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new Respuesta<>(e.getMessage()) );
    }

}
