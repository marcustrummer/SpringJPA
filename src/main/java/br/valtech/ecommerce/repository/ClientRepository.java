package br.valtech.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.valtech.ecommerce.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Optional<Client> findByCpf(String cpf);


}
