package Projet_Calbo.model;

import java.util.List;

public class Equipe {
    private Integer id;
    private String nom;
    private List<Members> members; // List of Members in the Equipe

    // No-argument constructor
    public Equipe() {
    }

    // Parameterized constructor including Members list
    public Equipe( String nom, List<Members> members) {
    
        this.nom = nom;
        this.members = members;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Members> getMembers() {
        return members;
    }

    public void setMembers(List<Members> members) {
        this.members = members;
    }
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", members=" + (members != null ? members.toString() : "[]") +
                '}';
    }

}
