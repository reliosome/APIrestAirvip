package com.airvip.APIrest.classes;
import jakarta.persistence.*;

@Entity
@Table(name = "Avion", schema = "dbo")
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private int avion_id;

    @Column(nullable = false)
    private String modele;
    @Column(nullable = false) // Champ obligatoire
    private String image;
    @Column(nullable = false)
    private int capacite;


    public Avion() {}

    public Avion(String modele, String image, int capacite) {
        this.modele = modele;
        this.image = image;
        this.capacite = capacite;
    }

    public String getModele() {
        return modele;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getImage() {
        return image;
    }

    public int getAvion_id() {
        return avion_id;
    }

    public void setAvion_id(int avion_id) {
        this.avion_id = avion_id;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }




}
