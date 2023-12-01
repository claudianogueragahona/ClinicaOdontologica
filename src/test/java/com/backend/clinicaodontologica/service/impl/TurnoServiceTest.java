package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    void registroDeNuevoTurnoDevuelveUnId() throws BadRequestException, ResourceNotFoundException {
        //Primero registro un paciente
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 1234, LocalDate.of(2023, 12, 24),
                new DomicilioEntradaDto("calle", 1234, "localidad", "provincia"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        //Segundo registro un odontologo

        OdontologoEntradaDto odontologoARegistrar = new OdontologoEntradaDto("ABCD", "Bruce", "Wayne");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.guardarOdontologo(odontologoARegistrar);

        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(LocalDateTime.of( LocalDate.of(2023, 12, 24), LocalTime.of(12,12)),pacienteSalidaDto.getId(), odontologoSalidaDto.getId());

        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);

        assertEquals(1L, turnoSalidaDto.getId());
    }

    @Test
    @Order(2)
    void listaDeTurnosNoEsVacia(){
        List<TurnoSalidaDto> turnoSalidaDtoList = turnoService.listarTurnos();

        assertFalse(turnoSalidaDtoList.isEmpty());
    }

    @Test
    @Order(3)
    void eliminarUnTurnoNoExistenteArrojaResourceNotFoundException() {

        assertThrows(ResourceNotFoundException.class, ()->turnoService.eliminarTurno(4L));
    }
}
