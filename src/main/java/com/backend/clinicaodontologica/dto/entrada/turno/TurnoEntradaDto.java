package com.backend.clinicaodontologica.dto.entrada.turno;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha")
    //@JsonProperty("fecha_ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaYHora;

    @NotNull(message = "El odontologo no puede ser nulo")
    @Valid
    private Long odontologoId;

    @NotNull(message = "El paciente no puede ser nulo")
    @Valid
    private Long pacienteId;

    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(LocalDateTime fechaYHora, Long odontologoId, Long pacienteId) {
        this.fechaYHora = fechaYHora;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}
