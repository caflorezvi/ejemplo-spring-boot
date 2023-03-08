package co.edu.uniquindio.biblioteca.servicios.excepciones;

public class PrestamoNoEncontradoException extends RuntimeException{

    public PrestamoNoEncontradoException(String mensaje){
        super(mensaje);
    }

}
