package com.airvip.APIrest.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Avion", schema = "dbo")
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avion_id;

    @Column(nullable = false)
    private String modele;

    @Column(nullable = false)
    private int capacite;

    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public Avion() {}

    public Avion(String modele, int capacite, List<Image> images) {
        this.modele = modele;
        this.capacite = capacite;
        this.setImages(images);
    }

    public int getAvion_id() {
        return avion_id;
    }

    public void setAvion_id(int avion_id) {
        this.avion_id = avion_id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
        if (images != null) {
            for (Image image : images) {
                image.setAvion(this);
            }
        }
    }
}
