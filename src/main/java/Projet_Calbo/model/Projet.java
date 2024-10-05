package Projet_Calbo.model;

import java.time.LocalDate;
import java.util.List;

public class Projet {

    private int id;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutProjet statut;
    private Equipe equipe;
    private List<Tache> taches;    
    private int totalTaches; 
    private int totalMembres; 

    public Projet() {
    }

    public Projet(String nom, String description, LocalDate dateDebut, LocalDate dateFin, 
                  StatutProjet statut, Equipe equipe, List<Tache> taches, 
                  int totalTaches, int totalMembres) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.equipe = equipe;
        this.taches = taches;
        this.totalTaches = totalTaches;
        this.totalMembres = totalMembres;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin; 
    }

    public StatutProjet getStatut() {
        return statut;
    }

    public void setStatut(StatutProjet statut) {
        this.statut = statut;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public int getMemberCount() {
        if (equipe != null && equipe.getMembers() != null) {
            return equipe.getMembers().size();
        }
        return 0;
    }

    public int getTotalTaches() {
        return totalTaches;
    }

    public void setTotalTaches(int totalTaches) {
        this.totalTaches = totalTaches;
    }

    public int getTotalMembres() {
        return totalMembres;
    }

    public void setTotalMembres(int totalMembres) {
        this.totalMembres = totalMembres;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", statut=" + statut +
                ", equipe=" + equipe +
                ", totalTaches=" + totalTaches +
                ", totalMembres=" + totalMembres +
                '}';
    }
}
