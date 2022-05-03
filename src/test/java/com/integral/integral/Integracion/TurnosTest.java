package com.integral.integral.Integracion;


import com.integral.integral.entities.Domicilio;
import com.integral.integral.entities.Odontologo;
import com.integral.integral.entities.Paciente;
import com.integral.integral.entities.Turno;
import com.integral.integral.service.OdontologoService;
import com.integral.integral.service.PacienteService;
import com.integral.integral.service.TurnoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnosTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    TurnoService turnoService;

    public void cargarDatos(){
        Domicilio domicilio= new Domicilio();
        Paciente paciente = pacienteService.guardar(new Paciente("Mirra","Benjamín","benja@gmail.com", 40953309, LocalDate.of(2022,01,01),domicilio));
        Odontologo odontologo = new Odontologo("Benjamín", "Mirra", "123s");

        Turno turno = new Turno(paciente, odontologo, LocalDate.of(2022,05,01) );

    }

    @Test
    public void listarTurnos() throws Exception {
        this.cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
