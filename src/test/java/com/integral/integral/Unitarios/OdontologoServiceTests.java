package com.integral.integral.Unitarios;

import com.integral.integral.entities.Odontologo;
import com.integral.integral.exception.BadRequestException;
import com.integral.integral.exception.ResourceNotFoundException;
import com.integral.integral.service.OdontologoService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTests {

    @Autowired
    private OdontologoService odontologoService;


    public Odontologo cargarDatos(){
        Odontologo odontologo = new Odontologo("Benjamín", "Mirra", "AA201");
       odontologoService.guardar(odontologo);
       return odontologo;
    }

    @Test
    public void BbuscarOdontologoTest(){
        Assert.assertNotNull(odontologoService.buscar(1L));
    }

    @Test
    public void AcargarOdontologoTest() throws BadRequestException {
        Odontologo odontologo = this.cargarDatos();

        Assert.assertNotNull(odontologo);
    }

    @Test
    public void CactualizarOdontologoTest(){
        Odontologo odontologo = odontologoService.actualizar(new Odontologo(1L,"Jimena", "Balverde","BBB201"));
        Assert.assertEquals("BBB201", odontologo.getMatricula());
    }

    @Test
    public void DlistarOdontologosTest() {
        Assert.assertNotNull(odontologoService.listarOdontologos());
    }

    @Test
    public void EeliminarOdontologoTest() throws ResourceNotFoundException {
        odontologoService.eliminar(1L);
        Assert.assertFalse(odontologoService.buscar(1L).isPresent());
    }
}
