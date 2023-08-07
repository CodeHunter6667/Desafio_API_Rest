package com.rafaelehlert.clientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelehlert.clientes.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
