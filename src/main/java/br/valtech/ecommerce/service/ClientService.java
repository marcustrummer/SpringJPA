package br.valtech.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Optional<Client> findClientByCPF(String cpf) {
		return repository.findByCpf(cpf);
	}
	
	
	public Optional<Client> createClient(Client client) {

		if (repository.findByCpf(client.getCpf()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The client already exists!", null);

//		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
//
//		if (idade < 18)
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário é menos de idade!", null);

		return Optional.of(repository.save(client));
	}

	public Optional<Client> updateClient(Client client) {

		if (repository.findByCpf(client.getCpf()).isPresent()) {

			Optional<Client> findClient = repository.findByCpf(client.getCpf());

			if (findClient.isPresent()) {

				if (findClient.get().getCpf() != client.getCpf())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The client already exists!", null);

			}

//			int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
//			if (idade < 13)
//				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário é menor de idade", null);
//			usuario.setSenha(encoder(usuario.getSenha()));

			return Optional.of(repository.save(client));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!", null);
		}
	}
	
	
	//Create setInative method to forbid client of purchasing but retaning his sells
	

}
