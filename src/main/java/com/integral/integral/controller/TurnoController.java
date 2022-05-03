package com.integral.integral.controller;

import com.integral.integral.entities.Turno;
import com.integral.integral.exception.BadRequestException;
import com.integral.integral.exception.ResourceNotFoundException;
import com.integral.integral.service.OdontologoService;
import com.integral.integral.service.PacienteService;
import com.integral.integral.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
        return ResponseEntity.ok(turnoService.guardar(turno));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listar());
    }

    @PutMapping
    public Turno actualizar(@RequestBody Turno turno){
        return turnoService.actualizar(turno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscar(@PathVariable Long id){
        if(turnoService.buscar(id).isPresent()){
            return ResponseEntity.ok(turnoService.buscar(id).get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminar(id);
        return ResponseEntity.ok("Se elimino el Turno sin problemas");
    }
}
