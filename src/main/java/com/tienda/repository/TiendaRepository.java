package com.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.models.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda, Integer> {
    

}
