package com.backend.clinicaodontologica.service.impl;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.model.Turno;
import com.backend.clinicaodontologica.repository.TurnoRepository;
import com.backend.clinicaodontologica.service.ITurnoService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TurnoService implements ITurnoService {
    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    private TurnoRepository turnoRepository;

    private final ModelMapper modelMapper;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        configureMapping();
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException, ResourceNotFoundException {

        Long pacienteId = turnoEntradaDto.getPacienteId();
        Long odontologoId = turnoEntradaDto.getOdontologoId();


        PacienteSalidaDto pacienteTurno = pacienteService.buscarPacientePorId(pacienteId);
        LOGGER.info("Paciente Turno: {}", JsonPrinter.toString(pacienteTurno));
        OdontologoSalidaDto odontologoTurno = odontologoService.buscarOdontologoPorId(odontologoId);
        LOGGER.info("Paciente Turno: {}", JsonPrinter.toString(odontologoTurno));
        if(pacienteTurno == null){
            throw new BadRequestException("Este paciente no existe");
        }
        if(odontologoTurno == null){
            throw new BadRequestException("Este odontologo no existe");
        }
        if(turnoEntradaDto.getFechaYHora() == null){
            throw new BadRequestException("No se ha especificado la fecha/hora del turno");
        }

        Paciente pacienteEntidad = modelMapper.map(pacienteTurno, Paciente.class);
        LOGGER.info("Paciente Entidad: {}", JsonPrinter.toString(pacienteEntidad));

        Odontologo odontologoEntidad = modelMapper.map(odontologoTurno, Odontologo.class);
        LOGGER.info("Odontologo Entidad: {}", JsonPrinter.toString(odontologoTurno));

        Turno turnoEntidad = new Turno();
        turnoEntidad.setPaciente(pacienteEntidad);
        turnoEntidad.setOdontologo(odontologoEntidad);
        turnoEntidad.setFechaYHora(turnoEntradaDto.getFechaYHora());
        LOGGER.info("Turno Entidad: {}", JsonPrinter.toString(turnoEntidad));


        Turno turnoAPersistir = turnoRepository.save(turnoEntidad);
        LOGGER.info("Turno a Persistir: {}", JsonPrinter.toString(turnoAPersistir));

        TurnoSalidaDto turnoSalidaDto = crearTurnoSalidaDto(turnoAPersistir);
        LOGGER.info("TurnoSalidaDto: {}", JsonPrinter.toString(turnoSalidaDto));
        return turnoSalidaDto;
    }



    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnoSalidaDto = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();

        if (LOGGER.isInfoEnabled())
            LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnoSalidaDto));
        return turnoSalidaDto;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null) {
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return turnoEncontrado;
    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoModificacionEntradaDto turno) {
        Turno turnoRecibido = modelMapper.map(turno, Turno.class);
        Turno turnoAActualizar = turnoRepository.findById(turnoRecibido.getId()).orElse(null);

        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAActualizar != null) {
            turnoAActualizar = turnoRecibido;
            turnoRepository.save(turnoAActualizar);

            turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
            //lanzar excepcion correspondiente
        }


        return turnoSalidaDto;
    }

    @Override
    public void eliminarTurno(Long id) {
        if (turnoRepository.findById(id).orElse(null) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
            //excepcion a lanzar aqui
        }
    }


        private void configureMapping(){
            modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                    .addMappings(modelMapper -> modelMapper.map
                            (PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
            modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class).
                    addMappings(modelMapper->modelMapper.map
                            (Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
            modelMapper.typeMap(OdontologoEntradaDto.class, Odontologo.class);
            modelMapper.typeMap(Odontologo.class, OdontologoSalidaDto.class);

    }
    private TurnoSalidaDto crearTurnoSalidaDto(Turno turno) {
        TurnoSalidaDto turnoSalidaDto = new TurnoSalidaDto();

        turnoSalidaDto.setId(turno.getId());
        turnoSalidaDto.setFechaYHora(turno.getFechaYHora());
        turnoSalidaDto.setPaciente_id(turno.getPaciente().getId());
        turnoSalidaDto.setNombrePaciente(turno.getPaciente().getNombre());
        turnoSalidaDto.setOdontologo_id(turno.getOdontologo().getId());
        turnoSalidaDto.setNombreOdontologo(turno.getOdontologo().getNombre());

        return turnoSalidaDto;
    }
}
