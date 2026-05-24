package spring.crudJdbc.demo.controller;

import spring.crudJdbc.demo.model.Dipendente;
import spring.crudJdbc.demo.model.Genere;
import spring.crudJdbc.demo.repository.DipendenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//1. Test Unitario Puro (Niente Spring, solo Java e Mockito)
//In questo test notiamo la potenza del costruttore: 
//passiamo il finto repository (Mock) direttamente a mano. 
//È un test che si esegue in pochi millisecondi.
class DipendenteControllerUnitTest {

    private DipendenteRepository repositoryMock;
    private DipendenteController controller;

    @BeforeEach
    void setUp() {
        // Creiamo il finto repository (Mock)
        repositoryMock = Mockito.mock(DipendenteRepository.class);
        // Lo passiamo direttamente al costruttore del controller
        controller = new DipendenteController(repositoryMock);
    }

    @Test
    void testGetById_DipendenteEsistente_Ritorna200Eoggetto() {
        // GIVEN: prepariamo i finti dati
        Long idTest = 1L;
        Dipendente fintoDipendente = new Dipendente(idTest, "Mario", "Rossi", "RSSMRA80A01F205Z", 
                Genere.MASCHIO, LocalDate.of(1980, 1, 1), "Napoli", "Laurea", "Developer");
        
        // Istruiamo il mock: "Quando qualcuno ti chiede l'ID 1, rispondi con il finto dipendente"
        when(repositoryMock.findById(idTest)).thenReturn(Optional.of(fintoDipendente));

        // WHEN: eseguiamo il metodo del controller
        ResponseEntity<Dipendente> risposta = controller.getById(idTest);

        // THEN: verifichiamo i risultati
        assertEquals(HttpStatus.OK, risposta.getStatusCode());
        assertEquals("Mario", risposta.getBody().getNome());
    }

    @Test
    void testGetById_DipendenteNonEsistente_Ritorna404() {
        // GIVEN: l'ID non esiste nel DB
        Long idInesistente = 999L;
        when(repositoryMock.findById(idInesistente)).thenReturn(Optional.empty());

        // WHEN
        ResponseEntity<Dipendente> risposta = controller.getById(idInesistente);

        // THEN
        assertEquals(HttpStatus.NOT_FOUND, risposta.getStatusCode());
    }
}