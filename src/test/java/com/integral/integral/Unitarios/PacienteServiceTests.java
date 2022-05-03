package com.integral.integral.Unitarios;

import com.integral.integral.entities.Paciente;
import com.integral.integral.exception.BadRequestException;
import com.integral.integral.exception.ResourceNotFoundException;
import com.integral.integral.service.PacienteService;
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
public class PacienteServiceTests {

    @Autowired
    private PacienteService pacienteService;


    public Paciente cargarDatos(){
        Paciente paciente = new Paciente("Mirra","Benjamín", "mirra@gmail.com", 40953309, LocalDate.of(2022,04,01), null);
        pacienteService.guardar(paciente);
        return paciente;
    }

    @Test
    public void BbuscarPacienteTest(){
        Assert.assertNotNull(pacienteService.buscar(1L));
    }

    @Test
    public void AcargarPacienteTest() throws BadRequestException {
        Paciente paciente = this.cargarDatos();

        Assert.assertNotNull(paciente);
    }

    @Test
    public void CactualizarPacienteTest(){
        Paciente paciente = pacienteService.actualizar(new Paciente(1L,"Balverde","Benjamín", "mirra@gmail.com", 40953309, LocalDate.of(2022,04,01), null));
        String apellidoViejo = "Mirra";
        Assert.assertNotEquals(apellidoViejo, paciente.getApellido());
    }

    @Test
    public void DlistarPacientesTest() {
        Assert.assertNotNull(pacienteService.listarPacientes());
    }

    @Test
    public void EeliminarPacienteTest() throws ResourceNotFoundException {
        pacienteService.eliminar(1L);
        Assert.assertFalse(pacienteService.buscar(1L).isPresent());
    }
}
