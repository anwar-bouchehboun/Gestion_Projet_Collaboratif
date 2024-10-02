package Projet_Calbo.model;

import java.time.LocalDate;

public class Tache {
    private int id;
    private String titre;
    private String description;
    private PrioriteEnum priorite;
    private Statut statut;
    private LocalDate dateCreation;
    private LocalDate dateEcheance;
    private Projet projet;
    private Members membre;

    public Tache() {}

    public Tache(String titre, String description, PrioriteEnum priorite, Statut statut, 
                 LocalDate dateCreation, LocalDate dateEcheance, Projet projet, Members membre) {
        this.titre = titre;
        this.description = description;
        this.priorite = priorite;
        this.statut = statut;
        this.dateCreation = dateCreation;
        this.dateEcheance = dateEcheance;
        this.projet = projet;
        this.membre = membre;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PrioriteEnum getPriorite() {
        return priorite;
    }

    public void setPriorite(PrioriteEnum priorite) {
        this.priorite = priorite;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Members getMembre() {
        return membre;
    }

    public void setMembre(Members membre) {
        this.membre = membre;
    }
}
