package com.tienda.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.models.Tienda;
import com.tienda.repository.TiendaRepository;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/tienda")
@RequiredArgsConstructor
public class TiendaController{

    private final TiendaRepository tiendaRepository;

    @GetMapping
    public ResponseEntity<List<Tienda>> getAllTiendas() {
        List<Tienda> tiendas = tiendaRepository.findAll();
        return new ResponseEntity<>(tiendas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tienda> getTiendaById(@PathVariable Integer id) {
        return tiendaRepository.findById(id)
                .map(tienda -> new ResponseEntity<>(tienda, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Tienda> createTienda(@RequestBody Tienda tienda) {
        Tienda savedTienda = tiendaRepository.save(tienda);
        return new ResponseEntity<>(savedTienda, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tienda> updateTienda(@PathVariable Integer id, @RequestBody Tienda tienda) {
        if (!tiendaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tienda.setIdTienda(id);
        Tienda updatedTienda = tiendaRepository.save(tienda);
        return new ResponseEntity<>(updatedTienda, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTienda(@PathVariable Integer id) {
        if (!tiendaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tiendaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    
    



}
