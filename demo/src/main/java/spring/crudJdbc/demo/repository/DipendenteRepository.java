package spring.crudJdbc.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring.crudJdbc.demo.model.Dipendente;

import java.util.Optional;

@Repository
public interface DipendenteRepository extends CrudRepository<Dipendente, Long> {
    // Se serve, un metodo custom per cercare via Codice Fiscale
    Optional<Dipendente> findByCodiceFiscale(String codiceFiscale);
}
