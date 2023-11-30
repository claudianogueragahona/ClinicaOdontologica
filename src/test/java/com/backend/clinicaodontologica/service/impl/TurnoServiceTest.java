package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void registroDeNuevoTurnoDevuelveUnId() {

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Catalina", "Noguera", 268423, LocalDate.of(2023, 12, 23),
                new DomicilioEntradaDto("Central", 524, "Viña del Mar", "Valparaiso"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
    }

    @Test
    @Order(2)
    void deberiaListarLosTurnos(){
        List<TurnoSalidaDto> turnoSalidaDtoList = turnoService.listarTurnos();

        assertFalse(turnoSalidaDtoList.isEmpty());
    }
}