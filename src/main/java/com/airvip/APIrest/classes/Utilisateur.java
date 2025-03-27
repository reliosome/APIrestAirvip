package com.airvip.APIrest.classes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "Utilisateur", schema = "dbo")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private int id_utilisateur;

    @Column(nullable = false)
    @NotBlank(message = "Le rôle est obligatoire")
    private String role;

    @Column(nullable = false, name = "adresse_courriel")
    @Email(message = "L'adresse courriel doit être valide")
    @NotBlank(message = "L'adresse courriel est obligatoire")
    private String adresseCourriel;

    @Column(nullable = false) // Champ obligatoire
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @Column(nullable = false)
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @Column(nullable = false)
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String mot_de_passe;

    public Utilisateur() {}

    public Utilisateur(String role, String adresse_courriel, String prenom, String nom, String mot_de_passe) {
        this.role = role;
        this.adresseCourriel = adresse_courriel;
        this.prenom = prenom;
        this.nom = nom;
        this.mot_de_passe = mot_de_passe;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse_courriel() {
        return adresseCourriel;
    }

    public void setAdresse_courriel(String courriel) {
        this.adresseCourriel = courriel;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMot_de_passe() {
        return "********"; // Ne pas exposer le mot de passe
    }


    public void setMot_de_passe(String mot_de_passe) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.mot_de_passe = encoder.encode(mot_de_passe);
    }
}
