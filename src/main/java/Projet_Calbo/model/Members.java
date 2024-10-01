package Projet_Calbo.model;

public class Members {
	private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private Role role;
    private Equipe equipe;

    // No-argument constructor
    public Members() {
    }

    // Parameterized constructor with Equipe
    public Members(String nom, String prenom, String email, Role role, Equipe equipe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.equipe = equipe;
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
}
