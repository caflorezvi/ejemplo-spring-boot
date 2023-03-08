package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.PrestamoGetDTO;
import co.edu.uniquindio.biblioteca.dto.PrestamoPostDTO;
import co.edu.uniquindio.biblioteca.dto.PrestamoQueryDTO;
import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.entity.Libro;
import co.edu.uniquindio.biblioteca.entity.Prestamo;
import co.edu.uniquindio.biblioteca.repo.ClienteRepo;
import co.edu.uniquindio.biblioteca.repo.LibroRepo;
import co.edu.uniquindio.biblioteca.repo.PrestamoRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.ClienteNoEncontradoException;
import co.edu.uniquindio.biblioteca.servicios.excepciones.LibroNoEncontradoException;
import co.edu.uniquindio.biblioteca.servicios.excepciones.PrestamoNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PrestamoServicio {

    private final PrestamoRepo prestamoRepo;
    private final ClienteRepo clienteRepo;
    private final LibroRepo libroRepo;

    public long save(PrestamoPostDTO prestamoDTO){

        long codigoCliente = prestamoDTO.clienteID();
        Optional<Cliente> consulta = clienteRepo.findById(codigoCliente);

        if(consulta.isEmpty()){
            throw new ClienteNoEncontradoException("No existe un cliente con el código "+codigoCliente);
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setCliente(consulta.get());
        prestamo.setFechaPrestamo(LocalDateTime.now());

        List<Libro> buscados = getLibros(prestamoDTO.isbnLibros());

        prestamo.setLibros(buscados);
        prestamo.setFechaDevolucion(prestamoDTO.fechaDevolucion());

        Prestamo guardado = prestamoRepo.save(prestamo);
        return guardado.getCodigo();
    }

    public List<PrestamoGetDTO> findByCodigoCliente(long codigoCliente){

        clienteRepo.findById(codigoCliente).orElseThrow(() -> new ClienteNoEncontradoException("No existe un cliente con el código "+codigoCliente));

        List<PrestamoQueryDTO> lista = prestamoRepo.findByCodigoCliente(codigoCliente);
        List<PrestamoGetDTO> respuesta = new ArrayList<>();

        for(PrestamoQueryDTO q : lista){
            if(respuesta.stream().noneMatch(r -> r.prestamoID() == q.getPrestamoID())){
                ArrayList<String> libros = new ArrayList<>();
                libros.add(q.getIsbnLibro());
                respuesta.add( new PrestamoGetDTO(q.getPrestamoID(), q.getClienteID(), libros, q.getFechaCreacion(), q.getFechaDevolucion() ) );
            }else{
                respuesta.stream().findAny().get().isbnLibros().add( q.getIsbnLibro() );
            }
        }

        return respuesta;

    }

    public List<PrestamoGetDTO> findAll(){
        return prestamoRepo.findAll().stream()
                .map(this::convertir)
                .toList();
    }

    public PrestamoGetDTO findById(long codigoPrestamo){
        Prestamo prestamo = prestamoRepo.findById(codigoPrestamo).orElseThrow(()-> new PrestamoNoEncontradoException("No existe un préstamo con el código: "+codigoPrestamo));
        return convertir(prestamo);
    }

    public List<PrestamoGetDTO> findByDate(LocalDate codigoPrestamo){
        return prestamoRepo.findByDate(codigoPrestamo)
                .stream()
                .map(this::convertir)
                .toList();
    }

    public long lendingCount(String isbn){
        libroRepo.findById(isbn).orElseThrow( () -> new LibroNoEncontradoException("No existe un libro con el isbn "+isbn) );
        return prestamoRepo.lendingCount(isbn);
    }

    public long update(long codigoPrestamo, PrestamoPostDTO prestamoPostDTO){
        Prestamo prestamo = prestamoRepo.findById(codigoPrestamo).orElseThrow(()-> new PrestamoNoEncontradoException("No existe un préstamo con el código: "+codigoPrestamo));

        List<Libro> buscados = getLibros(prestamoPostDTO.isbnLibros());
        prestamo.setLibros(buscados);
        prestamo.setFechaPrestamo(LocalDateTime.now());
        prestamo.setFechaDevolucion(prestamoPostDTO.fechaDevolucion());

        return prestamoRepo.save(prestamo).getCodigo();
    }

    private PrestamoGetDTO convertir(Prestamo p){

        return new PrestamoGetDTO(
                p.getCodigo(),
                p.getCliente().getCodigo(),
                p.getLibros().stream().map(Libro::getIsbn).toList(),
                p.getFechaPrestamo(),
                p.getFechaDevolucion()
        );

    }

    private List<Libro> getLibros(List<String> codigosLibros){
        List<Libro> buscados = libroRepo.findAllById(codigosLibros);

        if( buscados.size() != codigosLibros.size() ){
            throw new LibroNoEncontradoException("Hay códigos que no están asociados a ningún libro");
        }

        return buscados;
    }

}
