package com.tienda.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiendaDTO {

    private Integer idTienda;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;


}
