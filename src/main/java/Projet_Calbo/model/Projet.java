package Projet_Calbo.model;

import java.time.LocalDate;

public class Projet {

	private int id;
	private String nom;
	private String description ;
	private LocalDate dateDebut;
	private LocalDate dateFin ;
	
	
	
	public Projet( String nom ,String Description ,LocalDate dateDebut ,LocalDate dateFin, String description, Statut statut) {
		this.nom = nom;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.setStatut(statut);
	}
	
	
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
		this.nom=nom;
	}
	
	public String getDescription() {
		return description;
		
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut=dateDebut;
	}
	
	public LocalDate getDateFin() {
		return dateFin;
		
	}
	
	public void setDateFin(LocalDate dateFin) {
		this.dateDebut=dateFin;
	}

	
}
