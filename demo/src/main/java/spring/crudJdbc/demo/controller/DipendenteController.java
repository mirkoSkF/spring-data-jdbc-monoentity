package spring.crudJdbc.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.crudJdbc.demo.model.Dipendente;
import spring.crudJdbc.demo.repository.DipendenteRepository;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    private final DipendenteRepository repository;

    // Injection tramite costruttore
    public DipendenteController(DipendenteRepository repository) {
        this.repository = repository;
    }

    // READ ALL
    @GetMapping
    public Iterable<Dipendente> getAll() {
        return repository.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Dipendente> create(@RequestBody Dipendente dipendente) {
        // Forziamo l'id a null per garantire che Spring Data JDBC esegua sempre una INSERT nativa
        dipendente.setId(null);
        Dipendente saved = repository.save(dipendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> update(@PathVariable Long id, @RequestBody Dipendente dettagliDipendente) {
        // 1. Verifica preliminare dell'esistenza del record a sistema
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        // 2. Iniettiamo l'ID della risorsa preso dal path nell'oggetto da salvare.
        // In Spring Data JDBC, la presenza dell'ID istruisce il metodo save() a fare un'operazione di UPDATE.
        // Essendo una struttura a singola tabella piatta, sovrascriverà i campi sulla riga senza toccare altro.
        dettagliDipendente.setId(id);
        Dipendente updated = repository.save(dettagliDipendente);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}