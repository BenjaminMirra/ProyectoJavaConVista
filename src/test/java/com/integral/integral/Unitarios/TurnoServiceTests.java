package com.integral.integral.Unitarios;

import com.integral.integral.entities.Domicilio;
import com.integral.integral.entities.Odontologo;
import com.integral.integral.entities.Paciente;
import com.integral.integral.entities.Turno;
import com.integral.integral.exception.BadRequestException;
import com.integral.integral.exception.ResourceNotFoundException;
import com.integral.integral.service.OdontologoService;
import com.integral.integral.service.PacienteService;
import com.integral.integral.service.TurnoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTests {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    public void cargarDatos(){
        Domicilio domicilio = new Domicilio("Balcarce",16000,"Yerba Buena", "Tucumán");
        Paciente paciente = pacienteService.guardar( new Paciente("Balverde","Jimena","jimena@gmail.com",10101, LocalDate.of(2022,04,01), domicilio)
        );
        this.odontologoService.guardar(new Odontologo("Benjamin","Mirra","SAD21"));
    }

    @Test
    public void BbuscarTurnoTest(){
        Assert.assertNotNull(turnoService.buscar(1L));
    }

    @Test
    public void AregistroTurnoTest() throws BadRequestException {
        this.cargarDatos();
        Turno turno = turnoService.guardar(new Turno(pacienteService.buscar(1L).get(),odontologoService.buscar(1L).get(),LocalDate.of(2022,06,10)));
        Assert.assertNotNull(turno);
    }

    @Test
    public void CactualizarTurnoTest(){
        Turno turno = turnoService.actualizar(
                new Turno(1L,pacienteService.buscar(1L).get(),odontologoService.buscar(1L).get(),LocalDate.of(2022,10,1)));
        Assert.assertEquals(LocalDate.of(2022,10,1),turno.getFecha());
    }

    @Test
    public void DlistarTurnoTest() {
        Assert.assertNotNull(turnoService.listar());
    }

    @Test
    public void EeliminarTurnoTest() throws ResourceNotFoundException {
        turnoService.eliminar(1L);
        Assert.assertFalse(turnoService.buscar(1L).isPresent());
    }
}
