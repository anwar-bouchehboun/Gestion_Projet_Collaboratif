-- Table for Equipe (Team)
CREATE TABLE IF NOT EXISTS Equipe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

-- Table for Projet (Project)
CREATE TABLE IF NOT EXISTS Projet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    dateDebut DATE NOT NULL,
    dateFin DATE,
    statut ENUM('ENPREPARATION', 'ENCOURS', 'ENPAUSE', 'TERMINE', 'ANNULE') NOT NULL,
    INDEX idx_projet_statut (statut),
    equipe_id INT,
    FOREIGN KEY (equipe_id) REFERENCES Equipe(id) ON DELETE CASCADE
);

-- Table for Membre (Member)
CREATE TABLE IF NOT EXISTS Membre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role ENUM('CHEF_DE_PROJET', 'DEVELOPPEUR', 'DESIGNER') NOT NULL,
    UNIQUE KEY uk_membre_email (email),
    equipe_id INT,
    FOREIGN KEY (equipe_id) REFERENCES Equipe(id) ON DELETE CASCADE
);

-- Table for Tache (Task)
CREATE TABLE IF NOT EXISTS Tache (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    description TEXT,
    priorite ENUM('BASSE', 'MOYENNE', 'HAUTE') NOT NULL,
    statut ENUM('AFAIRE', 'ENCOURS', 'TERMINE') NOT NULL,
    dateCreation DATE NOT NULL,
    dateEcheance DATE,
    projet_id INT NOT NULL,
    membre_id INT,
    FOREIGN KEY (projet_id) REFERENCES Projet(id) ON DELETE CASCADE,
    FOREIGN KEY (membre_id) REFERENCES Membre(id) ON DELETE SET NULL,
    INDEX idx_tache_projet (projet_id),
    INDEX idx_tache_membre (membre_id)
);

-- Sample data insertions
INSERT INTO Equipe (nom) VALUES 
('Équipe A'),
('Équipe B');

INSERT INTO Projet (nom, description, dateDebut, dateFin, statut, equipe_id) VALUES 
('Projet Alpha', 'Description du Projet Alpha', '2024-01-01', '2024-12-31', 'ENCOURS', 1),
('Projet Beta', 'Description du Projet Beta', '2024-03-01', '2024-09-30', 'TERMINE', 2);

INSERT INTO Membre (nom, prenom, email, role, equipe_id) VALUES 
('Dupont', 'Alice', 'alice.dupont@example.com', 'CHEF_DE_PROJET', 1),
('Martin', 'Bob', 'bob.martin@example.com', 'DEVELOPPEUR', 1),
('Leroy', 'Clara', 'clara.leroy@example.com', 'DESIGNER', 1);

INSERT INTO Tache (titre, description, priorite, statut, dateCreation, dateEcheance, projet_id, membre_id) VALUES 
('Tâche 1', 'Description de la tâche 1', 'MOYENNE', 'AFAIRE', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), 1, 1),
('Tâche 2', 'Description de la tâche 2', 'HAUTE', 'ENCOURS', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 60 DAY), 1, 2),
('Tâche 3', 'Description de la tâche 3', 'BASSE', 'TERMINE', CURDATE(), DATE_ADD(CURDATE(), INTERVAL 90 DAY), 2, 3);

-- Grant permissions to a user (replace 'username' and 'password' with actual values)
CREATE USER IF NOT EXISTS 'anwar'@'localhost' IDENTIFIED BY 'javaaura';
GRANT SELECT, INSERT, UPDATE, DELETE ON collaboratif.* TO 'anwar'@'localhost';
FLUSH PRIVILEGES;
