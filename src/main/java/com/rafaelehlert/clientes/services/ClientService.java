package com.rafaelehlert.clientes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelehlert.clientes.dto.ClientDTO;
import com.rafaelehlert.clientes.entities.Client;
import com.rafaelehlert.clientes.repositories.ClientRepository;
import com.rafaelehlert.clientes.services.exceptions.DataAllreadyInsertedException;
import com.rafaelehlert.clientes.services.exceptions.ClientNotFoundException;

import jakarta.persistence.EntityNotFoundException;



@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;

    //Busca por Id
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(
            () -> new ClientNotFoundException("Cliente nao encontrado"));
        return new ClientDTO(client);
    }

    //Buscar todos os clientes
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pegeable){
        Page<Client> result = repository.findAll(pegeable);
        return result.map(x -> new ClientDTO(x));
    }

    //Inserir novos dados
    @Transactional(propagation = Propagation.SUPPORTS)
    public ClientDTO insert(ClientDTO dto){
        try{
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DataAllreadyInsertedException("Cpf já cadastrado");
        }
    }

    //Atualiza dados existentes
    @Transactional(propagation = Propagation.SUPPORTS)
    public ClientDTO update(Long id, ClientDTO dto){
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ClientNotFoundException("Cliente não encontrado");
        } catch (DataIntegrityViolationException e){
            throw new DataAllreadyInsertedException("Cpf já cadastrado");
        }
    }

    //Deleta dados existentes
    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ClientNotFoundException("Cliente não encontrado");
        }
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {

        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
