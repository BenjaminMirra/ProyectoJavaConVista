package com.integral.integral.service;

import com.integral.integral.entities.Odontologo;
import com.integral.integral.entities.Paciente;
import com.integral.integral.entities.Turno;
import com.integral.integral.exception.BadRequestException;
import com.integral.integral.exception.ResourceNotFoundException;
import com.integral.integral.repository.OdontologoRepository;
import com.integral.integral.repository.PacienteRepository;
import com.integral.integral.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    TurnoRepository repository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    OdontologoRepository odontologoRepository;

    public List<Turno> listar(){
        return repository.findAll();
    }

    public Turno guardar(Turno turno) throws BadRequestException {
        Optional<Paciente> paciente = pacienteRepository.findById(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoRepository.findById(turno.getOdontologo().getId());

        if(paciente.isPresent() && odontologo.isPresent()){
            return repository.save(turno);
        }else{
            throw new BadRequestException("No se puede registrar el turno porque el paciente o el odontólogo no existen.");
        }
    }

    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Turno> turnoBuscado= buscar(id);
        if (turnoBuscado.isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException("No existe el turno con el id= "+id+". Ingresar un id correcto");
    }

    public Optional<Turno> buscar(Long id){
        return repository.findById(id);
    }

    public Turno actualizar(Turno turno) {
        if(buscar(turno.getId()).isPresent())
            return repository.save(turno);
        else
            return null;
    }


}
