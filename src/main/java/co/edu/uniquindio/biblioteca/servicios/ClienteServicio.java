package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.ClienteGet;
import co.edu.uniquindio.biblioteca.dto.ClientePost;
import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.repo.ClienteRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.ClienteNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    public ClienteGet save(ClientePost cliente){
        return convertir( clienteRepo.save( convertir(cliente) ) );
    }


    public ClienteGet findById(Long codigoCliente){
        Cliente cliente = obtenerCliente(codigoCliente);
        return convertir(cliente);
    }

    public void delete(long codigoCliente){
        obtenerCliente(codigoCliente);
        clienteRepo.deleteById(codigoCliente);
    }

    public ClienteGet update(long codigoCliente, ClientePost clienteNuevo){
        obtenerCliente(codigoCliente);

        Cliente nuevo = convertir(clienteNuevo);
        nuevo.setCodigo(codigoCliente);
        return convertir( clienteRepo.save(nuevo) );
    }

    public List<ClienteGet> findAll(){
        return clienteRepo.findAll()
                .stream()
                .map(c -> convertir(c))
                .collect(Collectors.toList());
    }

    private Cliente obtenerCliente(Long codigoCliente){
        return clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
    }

    private ClienteGet convertir(Cliente cliente){
        return new ClienteGet(cliente.getCodigo(), cliente.getNombre(), cliente.getEmail(), cliente.getTelefono());
    }

    private Cliente convertir(ClientePost cliente){
        return Cliente.builder()
                .nombre(cliente.nombre())
                .email(cliente.email())
                .telefono(cliente.telefono())
                .password(cliente.password()).build();
    }

}
