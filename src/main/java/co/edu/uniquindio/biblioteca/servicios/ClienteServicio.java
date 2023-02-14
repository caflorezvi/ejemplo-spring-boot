package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.repo.ClienteRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    public Cliente save(Cliente cliente){
        return clienteRepo.save(cliente);
    }


    public Cliente findById(Long codigoCliente){
        return clienteRepo.findById(codigoCliente).orElseThrow( () -> new RuntimeException("El cliente no existe") );
    }


    public List<Cliente> findAll(){
        return clienteRepo.findAll();
    }

}
