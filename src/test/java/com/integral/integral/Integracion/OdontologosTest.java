package com.integral.integral.Integracion;

import com.integral.integral.entities.Odontologo;
import com.integral.integral.service.OdontologoService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologosTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OdontologoService odontologoService;

    public void cargarDatos(){
        Odontologo odontologo = new Odontologo("Benjamín", "Mirra", "123s");
    }

    @Test
    public void listarOdontologos() throws Exception {
        this.cargarDatos();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/odontologos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Assert.assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
