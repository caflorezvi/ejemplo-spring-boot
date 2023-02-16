package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.ClienteGet;
import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.repo.ClienteRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.ClienteNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    public Cliente save(Cliente cliente){
        return clienteRepo.save(cliente);
    }


    public ClienteGet findById(Long codigoCliente){
        Cliente cliente = clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );

        return convertir(cliente);
    }

    private ClienteGet convertir(Cliente cliente){
        return new ClienteGet(cliente.getNombre(), cliente.getEmail(), cliente.getTelefono());
    }

    public void delete(long codigoCliente){
        clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
        clienteRepo.deleteById(codigoCliente);
    }

    public Cliente update(long codigoCliente, Cliente clienteNuevo){
        clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
        //clienteNuevo.setCodigo(codigoCliente);
        return clienteRepo.save(clienteNuevo);
    }

    public List<ClienteGet> findAll(){
        return clienteRepo.findAll()
                .stream()
                .map(c -> convertir(c))
                .collect(Collectors.toList());
    }

}
