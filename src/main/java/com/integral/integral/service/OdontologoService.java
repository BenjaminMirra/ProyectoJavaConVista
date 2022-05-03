package com.integral.integral.service;

import com.integral.integral.entities.Odontologo;
import com.integral.integral.exception.ResourceNotFoundException;
import com.integral.integral.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    OdontologoRepository repository;

    public List<Odontologo> listarOdontologos(){
        return repository.findAll();
    }

    public Odontologo guardar(Odontologo odontologo){
        return repository.save(odontologo);
    }

    public Optional<Odontologo> buscar(Long id){
        return repository.findById(id);
    }

    public Odontologo actualizar(Odontologo odontologo) {
        if(buscar(odontologo.getId()).isPresent()){
            return repository.save(odontologo);
        }else{
            return null;
        }
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = buscar(id);
        if(odontologoBuscado.isPresent()){
            repository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("No existe el odontólogo con el id = " + id + ". Ingresar un id correcto.");
        }
    }
}
