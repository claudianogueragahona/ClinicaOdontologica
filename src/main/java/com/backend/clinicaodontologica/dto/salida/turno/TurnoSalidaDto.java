package com.backend.clinicaodontologica.dto.salida.turno;

import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import java.time.LocalDateTime;

public class TurnoSalidaDto {
    private Long id;
    private LocalDateTime fechaYHora;
    private Long odontologo_id;
    private String nombreOdontologo;
    private Long paciente_id;
    private String nombrePaciente;

    public TurnoSalidaDto() {
    }

    public TurnoSalidaDto(Long id, LocalDateTime fechaYHora, Long odontologo_id, String nombreOdontologo, Long paciente_id, String nombrePaciente) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologo_id = odontologo_id;
        this.nombreOdontologo = nombreOdontologo;
        this.paciente_id = paciente_id;
        this.nombrePaciente = nombrePaciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Long getOdontologo_id() {
        return odontologo_id;
    }

    public void setOdontologo_id(Long odontologo_id) {
        this.odontologo_id = odontologo_id;
    }

    public String getNombreOdontologo() {
        return nombreOdontologo;
    }

    public void setNombreOdontologo(String nombreOdontologo) {
        this.nombreOdontologo = nombreOdontologo;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
}
