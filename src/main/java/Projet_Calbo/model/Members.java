package Projet_Calbo.model;

import java.util.ArrayList;
import java.util.List;

public class Members {
	private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private Role role;
    private Equipe equipe;
    List <Tache> tache;

    // No-argument constructor
    public Members() {
    }

    public List<Tache> getTache() {
        return tache;
    }


    public void setTache(List<Tache> tache) {
        this.tache = tache;
    }

    public Members(String nom, String prenom, String email, Role role, Equipe equipe,List<Tache> tache ) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.equipe = equipe;
        this.tache=new ArrayList<Tache>();
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
    @Override
    public String toString() {
        return "Members{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", equipe=" + (equipe != null ? equipe.getNom() : "null") +  
                '}';
    }

}
