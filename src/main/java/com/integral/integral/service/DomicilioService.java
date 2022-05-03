package com.integral.integral.service;

import com.integral.integral.entities.Domicilio;
import com.integral.integral.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    @Autowired
    DomicilioRepository repository;

    public List<Domicilio> listarDomicilios(){
        return repository.findAll();
    }

    public Domicilio guardar(Domicilio domicilio){
        return repository.save(domicilio);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }

    public Optional<Domicilio> buscar(Long id){
        return repository.findById(id);
    }

    public Domicilio actualizar(Domicilio domicilio) {
        if(buscar(domicilio.getId()).isPresent())
            return repository.save(domicilio);
        else
            return null;
    }
}
