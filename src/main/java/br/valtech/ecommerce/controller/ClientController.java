package br.valtech.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.valtech.ecommerce.entity.Client;
import br.valtech.ecommerce.service.ClientService;

@RestController
@RequestMapping ("/clients")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class ClientController {

	
	@Autowired
	private ClientService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<Client>> getAllClients	() {
		return ResponseEntity.ok(service.findAllClients());
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<Client> getByCpf(@PathVariable String cpf) {
		return service.findClientByCPF(cpf)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping("/registration")
	public ResponseEntity<Client> postClient(@RequestBody Client client) {
		return service.createClient(client)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PutMapping("/update")
	public ResponseEntity<Client> putClient(@RequestBody Client client) {

		return service.updateClient(client)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	
}
