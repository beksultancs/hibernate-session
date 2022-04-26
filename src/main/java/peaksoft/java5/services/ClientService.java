package peaksoft.java5.services;

import peaksoft.java5.exceptions.BadCredentialsException;
import peaksoft.java5.exceptions.NotFoundException;
import peaksoft.java5.models.Client;
import peaksoft.java5.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Beksultan
 */
public class ClientService implements AutoCloseable {

    private ClientRepository clientRepository = new ClientRepository();

    // register
    public void register(Client newClient) {

        Boolean exists = clientRepository.existsByEmail(newClient.getEmail());

        if (exists) {
            throw new IllegalStateException(
                    String.format("client with email = %s already in use!", newClient.getEmail())
            );
        }

        clientRepository.save(newClient);
    }

    // login
    public boolean login(String email, String password) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        "client with email = " + email + " not found!"
                ));

        if (password.equals(client.getPassword())) {
            return true;
        }

        throw new BadCredentialsException(
                "incorrect password"
        );
    }

    // find all
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public void close() throws Exception {
        clientRepository.close();
    }
}
