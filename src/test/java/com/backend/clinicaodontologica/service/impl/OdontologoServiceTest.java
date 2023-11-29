package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    @Test
    @Order(1)
    void noSeDeberiaRegistrarUnOdontologoSinMatricula() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("485768", "Mariela", "Lucena");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.guardarOdontologo(odontologoEntradaDto);
        assertTrue(odontologoSalidaDto.getMatricula() != null);
    }

}