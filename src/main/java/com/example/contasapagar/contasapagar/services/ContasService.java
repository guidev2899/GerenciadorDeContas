package com.example.contasapagar.contasapagar.services;

import com.example.contasapagar.contasapagar.entities.ContasEntity;
import com.example.contasapagar.contasapagar.repositories.ContasRepository;
import org.springframework.stereotype.Service;

@Service
public class ContasService {

    private final ContasRepository contasRepository;

    public ContasService(ContasRepository contasRepository) {
        this.contasRepository = contasRepository;
    }

    public void createConta(ContasEntity contasEntity){
        contasRepository.save(contasEntity);
    }
}
