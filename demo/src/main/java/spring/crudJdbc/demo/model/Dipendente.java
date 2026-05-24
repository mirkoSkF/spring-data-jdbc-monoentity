package spring.crudJdbc.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Table("DIPENDENTE")
public class Dipendente {

    @Id
    private Long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String genere; // Convertito da Genere (Enum) a String
    private LocalDate dataDiNascita;
    private String luogoNascita;
    private String contatto; 
    private String titoloStudio;
    private String ruoloAziendale;

    // Costruttore vuoto
    public Dipendente() {}

    // Costruttore pieno
    public Dipendente(Long id, String nome, String cognome, String codiceFiscale, String genere, 
                      LocalDate dataDiNascita, String luogoNascita, String contatto, String titoloStudio, String ruoloAziendale) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.genere = genere;
        this.dataDiNascita = dataDiNascita;
        this.luogoNascita = luogoNascita;
        this.contatto = contatto;
        this.titoloStudio = titoloStudio;
        this.ruoloAziendale = ruoloAziendale;
    }

    // Getter e Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getCodiceFiscale() { return codiceFiscale; }
    public void setCodiceFiscale(String codiceFiscale) { this.codiceFiscale = codiceFiscale; }

    public String getGenere() { return genere; }
    public void setGenere(String genere) { this.genere = genere; }

    public LocalDate getDataDiNascita() { return dataDiNascita; }
    public void setDataDiNascita(LocalDate dataDiNascita) { this.dataDiNascita = dataDiNascita; }

    public String getLuogoNascita() { return luogoNascita; }
    public void setLuogoNascita(String luogoNascita) { this.luogoNascita = luogoNascita; }

    public String getContatto() { return contatto; }
    public void setContatto(String contatto) { this.contatto = contatto; }

    public String getTitoloStudio() { return titoloStudio; }
    public void setTitoloStudio(String titoloStudio) { this.titoloStudio = titoloStudio; }

    public String getRuoloAziendale() { return ruoloAziendale; }
    public void setRuoloAziendale(String ruoloAziendale) { this.ruoloAziendale = ruoloAziendale; }
}