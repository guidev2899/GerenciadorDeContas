package com.example.contasapagar.contasapagar.services;

import com.example.contasapagar.contasapagar.entities.EnderecoEntity;
import com.example.contasapagar.contasapagar.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;


    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public void createEndereco(EnderecoEntity endereco){
        repository.save(endereco);
    }
}
