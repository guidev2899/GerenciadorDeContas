package com.example.contasapagar.contasapagar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contasapagar.contasapagar.entities.ContasEntity;

@Repository
public interface ContasRepository extends JpaRepository<ContasEntity, Long> {

}
