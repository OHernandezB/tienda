package com.tienda.service;

import com.tienda.models.Tienda;
import com.tienda.dto.TiendaDTO;
import com.tienda.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    private TiendaDTO toDTO(Tienda tienda) {
        return new TiendaDTO(
                tienda.getIdTienda(),
                tienda.getNombre(),
                tienda.getDireccion(),
                tienda.getTelefono(),
                tienda.getEmail()
        );
    }

    private Tienda toEntity (TiendaDTO dto) {
        Tienda tienda = new Tienda();
        tienda.setIdTienda(dto.getIdTienda());
        tienda.setNombre(dto.getNombre());
        tienda.setDireccion(dto.getDireccion());
        tienda.setTelefono(dto.getTelefono());
        tienda.setEmail(dto.getEmail());
        return tienda;

    }

    public TiendaDTO crear(TiendaDTO dto){
        Tienda tienda = toEntity(dto);
        return toDTO(tiendaRepository.save(tienda));
    }

    public List<TiendaDTO> listar() {
        return tiendaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TiendaDTO obtenerPorId(Integer id) {
        Tienda tienda = tiendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con id: " + id));
        return toDTO(tienda);
    }

    public TiendaDTO actualizar(Integer id, TiendaDTO dto) {
        Tienda existente = tiendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con id: " + id));
        existente.setNombre(dto.getNombre());
        existente.setDireccion(dto.getDireccion());
        existente.setTelefono(dto.getTelefono());
        existente.setEmail(dto.getEmail());

        return toDTO(tiendaRepository.save(existente));
    }

    public void eliminar(Integer id) {
        tiendaRepository.deleteById(id);
    }

}
