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
				//se dentro l'Optional c'è l'oggetto trovato,
				//il metodo .map() lo estrae automaticamente e lo passa 
				//nel corpo della risposta
				.map(ResponseEntity::ok)
				//build() chiude la risposta col timbro 404
				//senza build() la risposta non verrebbe chiusa
				.orElse(ResponseEntity.notFound().build());
		/*Versione estesa
		 return repository.findById(id)
        .map(dipendente -> ResponseEntity.status(HttpStatus.OK).body(dipendente))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		 */
	}

	//NB: il findAll() non necessita di ResponseEntity in quanto non 
	//c'è il rischio di avere 404; se non ci sono elementi viene ritornato
	//il valore: []
	//Diversamente il findById() può ritornare 404 e quindi impacchettiamo una
	//risposta che avvisa l'utente che la risorsa non è stata trovata

	// CREATE
	@PostMapping
	public ResponseEntity<Dipendente> create(@RequestBody Dipendente dipendente) {
		// Forziamo l'id a null per garantire che Spring Data JDBC esegua sempre una INSERT nativa
		dipendente.setId(null); //usiamo Long che è un wrapper
		Dipendente saved = repository.save(dipendente);
		/*
		 * SPIEGAZIONE DEL RETURN
         1)Impostiamo lo stato HTTP su 201 CREATED 
         (lo standard mondiale per dire "Operazione riuscita, la risorsa è stata creata").

		 2)Nel corpo della risposta (.body(saved)) restituiamo il dipendente appena salvato, 
		 che ora conterrà anche l'ID ufficiale assegnato dal database.
		 */
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
		/*ok è una scorciatoria per ridurre il return
		 // VERSIONE LUNGA (Esplicita)
			return ResponseEntity.status(HttpStatus.OK).body(updated);
		// VERSIONE CORTA (La tua)
			return ResponseEntity.ok(updated);
		 */
		return ResponseEntity.ok(updated);
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build(); //sigillo il corpo della risposta mantendolo senza info
	}
}
