package spring.crudJdbc.demo.controller;

import spring.crudJdbc.demo.model.Dipendente;
import spring.crudJdbc.demo.model.Genere;
import spring.crudJdbc.demo.repository.DipendenteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


@WebMvcTest(DipendenteController.class) // Dice a Spring di caricare SOLO la parte Web legata a questo controller
class DipendenteControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc; // Strumento per simulare le chiamate HTTP (GET, POST, ecc.)

    // Sostituisce il bean nel contesto di Spring con un Mock
    @MockitoBean
    private DipendenteRepository repositoryMock;

    @Test
    void testGetById_ViaHttp_RitornaJsonE200() throws Exception {
        // GIVEN
        Long idTest = 1L;
        Dipendente fintoDipendente = new Dipendente(idTest, "Mario", "Rossi", "RSSMRA80A01F205Z", 
                Genere.MASCHIO, LocalDate.of(1980, 1, 1), "Napoli", "Laurea", "Developer");
        
        when(repositoryMock.findById(idTest)).thenReturn(Optional.of(fintoDipendente));

        // WHEN & THEN: Simuliamo una vera GET HTTP alla nostra API
        mockMvc.perform(get("/api/dipendenti/{id}", idTest)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Verifica HTTP 200
                .andExpect(jsonPath("$.nome").value("Mario")) // Verifica il contenuto del JSON di risposta
                .andExpect(jsonPath("$.cognome").value("Rossi"))
                .andExpect(jsonPath("$.genere").value("MASCHIO"));
    }
}
