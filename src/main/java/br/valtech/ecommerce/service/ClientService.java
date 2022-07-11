package br.valtech.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.valtech.ecommerce.entity.Client;
import br.valtech.ecommerce.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public List<Client> findAllClients() {
		return repository.findAll();
	}
	
	public Page<Client> findAllClients(Pageable pageable) {
		return repository.findAll(pageable);
	}
	

	public Optional<Client> findClientByCPF(String cpf) {

		Optional<Client> clientFound = repository.findByCpf(cpf);

		if (clientFound.get().isClientStatus()) {
			return repository.findByCpf(cpf);
		}

		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The client account is not ative!");
		}

	}

	public Optional<Client> createClient(Client client) {

		if (repository.findByCpf(client.getCpf()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The client already exists!", null);

		return Optional.of(repository.save(client));
	}

	public Optional<Client> updateClient(Client client) {
		if (repository.findByCpf(client.getCpf()).isPresent()) {
			return Optional.of(repository.save(client));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!", null);
		}
	}

	// Create setInative method to forbid client of purchasing but retaning his
	// sells

}
