package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    @Test
    @Order(1)
    void noSeDeberiaRegistrarUnOdontologoSinMatricula() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(null, "Mariela", "Lucena");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.guardarOdontologo(odontologoEntradaDto);
        assertTrue(odontologoSalidaDto.getMatricula() != null);
    }

    @Test
    @Order(2)
    void deberiaRegistrarUnOdontologoDeNombreAlonso(){
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("25847", "Alonso", "De Ercilla");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.guardarOdontologo(odontologoEntradaDto);
        assertEquals("Alonso", odontologoSalidaDto.getNombre());
    }

    @Test
    @Order(3)
    void deberiaDevolverUnaListaNoVaciaDePacientes(){
        List<OdontologoSalidaDto> odontologoSalidaDtoList = odontologoService.listarOdontologos();

        assertFalse(odontologoSalidaDtoList.isEmpty());
    }

    @Test
    @Order(4)
    void deberiaVerificarQueLaListaDeOdontologosContieneUnOdontologoDeNombreAlonso(){
        List<OdontologoSalidaDto> odontologoSalidaDtoList = odontologoService.listarOdontologos();

        assertTrue(odontologoSalidaDtoList.stream().anyMatch(p -> p.getNombre().equals("Alonso")));
    }

}