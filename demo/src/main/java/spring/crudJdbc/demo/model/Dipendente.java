package spring.crudJdbc.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Table("DIPENDENTE") // Opzionale se il nome della classe coincide con la tabella
public class Dipendente {

    @Id
    private Long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private Genere genere;
    private LocalDate dataDiNascita;
    private String luogoNascita;
    private String titoloStudio;
    private String ruoloAziendale;

    // Costruttore vuoto, pieno, getter e setter
    public Dipendente() {}

    public Dipendente(Long id, String nome, String cognome, String codiceFiscale, Genere genere, 
                      LocalDate dataDiNascita, String luogoNascita, String titoloStudio, String ruoloAziendale) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.genere = genere;
        this.dataDiNascita = dataDiNascita;
        this.luogoNascita = luogoNascita;
        this.titoloStudio = titoloStudio;
        this.ruoloAziendale = ruoloAziendale;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getCodiceFiscale() { return codiceFiscale; }
    public void setCodiceFiscale(String codiceFiscale) { this.codiceFiscale = codiceFiscale; }

    public Genere getGenere() { return genere; }
    public void setGenere(Genere genere) { this.genere = genere; }

    public LocalDate getDataDiNascita() { return dataDiNascita; }
    public void setDataDiNascita(LocalDate dataDiNascita) { this.dataDiNascita = dataDiNascita; }

    public String getLuogoNascita() { return luogoNascita; }
    public void setLuogoNascita(String luogoNascita) { this.luogoNascita = luogoNascita; }

    public String getTitoloStudio() { return titoloStudio; }
    public void setTitoloStudio(String titoloStudio) { this.titoloStudio = titoloStudio; }

    public String getRuoloAziendale() { return ruoloAziendale; }
    public void setRuoloAziendale(String ruoloAziendale) { this.ruoloAziendale = ruoloAziendale; }
}