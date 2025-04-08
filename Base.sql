CREATE DATABASE prevision;
USE prevision;

CREATE TABLE Prevision (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    montant DECIMAL(10, 2) NOT NULL,
    date_prevision DATE NOT NULL
);

CREATE TABLE Depense (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_prevision INT,
    montant DECIMAL(10, 2) NOT NULL,
    date_depense DATE NOT NULL,
    FOREIGN KEY (id_prevision) REFERENCES Prevision(id)
);
