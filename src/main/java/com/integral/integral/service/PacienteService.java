package com.integral.integral.service;

import com.integral.integral.entities.Paciente;
import com.integral.integral.exception.ResourceNotFoundException;
import com.integral.integral.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService{
    @Autowired
    PacienteRepository repository;

    public List<Paciente> listarPacientes() {
        return repository.findAll();
    }

    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

    public Paciente actualizar(Paciente paciente) {
        if(buscar(paciente.getId()).isPresent())
            return repository.save(paciente);
        else
            return null;
    }

    public Optional<Paciente> buscar(Long id) {
        return repository.findById(id);
    }

    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado=buscar(id);
        if(pacienteBuscado.isPresent())
            repository.deleteById(id);
        else{
            throw new ResourceNotFoundException("No existe el paciente con el id= "+id+". Ingresar un id correcto");
        }
    }
}