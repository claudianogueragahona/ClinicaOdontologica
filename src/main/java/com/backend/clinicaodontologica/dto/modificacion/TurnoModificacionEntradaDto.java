package com.backend.clinicaodontologica.dto.modificacion;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoModificacionEntradaDto {
    @NotNull(message = "Debe proveerse el id del turno que se desea modificar")
    private Long id;

    @NotNull(message = "El odontologo no puede ser nulo")
    @Valid
    private OdontologoModificacionEntradaDto odontologoModificacionEntradaDto;

    @NotNull(message = "El paciente no puede ser nulo")
    @Valid
    private PacienteModificacionEntradaDto pacienteModificacionEntradaDto;

    public TurnoModificacionEntradaDto() {
    }

    public TurnoModificacionEntradaDto(Long id, OdontologoModificacionEntradaDto odontologoModificacionEntradaDto, PacienteModificacionEntradaDto pacienteModificacionEntradaDto) {
        this.id = id;
        this.odontologoModificacionEntradaDto = odontologoModificacionEntradaDto;
        this.pacienteModificacionEntradaDto = pacienteModificacionEntradaDto;
    }

    public Long getId() {
        return id;
    }

    public void setOdontologoModificacionEntradaDto(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) {
        this.odontologoModificacionEntradaDto = odontologoModificacionEntradaDto;
    }

    public void setPacienteModificacionEntradaDto(PacienteModificacionEntradaDto pacienteModificacionEntradaDto) {
        this.pacienteModificacionEntradaDto = pacienteModificacionEntradaDto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OdontologoModificacionEntradaDto getOdontologoModificacionEntradaDto() {
        return odontologoModificacionEntradaDto;
    }

    public void setOdontologoEntradaDto(OdontologoEntradaDto odontologoEntradaDto) {
        this.odontologoModificacionEntradaDto = odontologoModificacionEntradaDto;
    }

    public PacienteModificacionEntradaDto getPacienteModificacionEntradaDto() {
        return pacienteModificacionEntradaDto;
    }

    public void setOdontologoModificacionEntradaDto(PacienteModificacionEntradaDto pacienteModificacionEntradaDto) {
        this.pacienteModificacionEntradaDto = pacienteModificacionEntradaDto;
    }

}
