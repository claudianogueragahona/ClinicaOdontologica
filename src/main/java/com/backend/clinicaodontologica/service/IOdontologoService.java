package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoSalidaDto guardarOdontologo(OdontologoEntradaDto odontologo);
    List<OdontologoSalidaDto> listarOdontologos();
    OdontologoSalidaDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;
    OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologo);
    void eliminarOdontologo(Long id);

}
