drop database simple_dipendenti_db;
create database simple_dipendenti_db;
CREATE TABLE IF NOT EXISTS dipendente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    codice_fiscale VARCHAR(16) NOT NULL UNIQUE,
    genere VARCHAR(10) NOT NULL, -- Mappato come String in Java
    data_di_nascita DATE NOT NULL,
    luogo_nascita VARCHAR(100) NOT NULL,
    contatto VARCHAR(255),       -- Colonna aggiunta per gestire il contatto flat
    titolo_studio VARCHAR(100),
    ruolo_aziendale VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
