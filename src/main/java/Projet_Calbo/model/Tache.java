package Projet_Calbo.model;

import java.time.LocalDate;

public class Tache {
    private Integer id;
    private String titre;
    private String description;
    private Priorite priorite;
    private Statut statut;
    private LocalDate dateCreation;
    private LocalDate dateEcheance;

    // Constructor
    public Tache(Integer id, String titre, String description, Priorite priorite, Statut statut, LocalDate dateCreation, LocalDate dateEcheance) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.priorite = priorite;
        this.statut = statut;
        this.dateCreation = dateCreation;
        this.dateEcheance = dateEcheance;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
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

    @Override
    public String toString() {
        return "Tache{" +
                "id=" + id +
                ", titre='" + titre + "'" +
                ", description='" + description + "'" +
                ", priorite=" + priorite +
                ", statut=" + statut +
                ", dateCreation=" + dateCreation +
                ", dateEcheance=" + dateEcheance +
                "}";
    }
}