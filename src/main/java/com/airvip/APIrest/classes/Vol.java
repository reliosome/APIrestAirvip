package com.airvip.APIrest.classes;
import com.airvip.APIrest.controleurs.AeroportController;
import com.airvip.APIrest.controleurs.AvionController;
import com.airvip.APIrest.repository.AeroportRepository;
import com.airvip.APIrest.repository.AvionRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
@Entity
@Table(name = "Vol", schema = "dbo")
public class Vol {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-incrémentation
    private int vol_id;

    @Column(nullable = false)
    private double prix;

    @Column(nullable = false)
    private String disponibilite;

    @Column(nullable = false)
    private int nb_place;

    @Column(nullable = false)
    private double temps;

    // Relation ManyToOne avec Aeroport pour le départ
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_aeroport_depart")
    private Aeroport aeroportDepart;

    // Relation ManyToOne avec Aeroport pour l'arrivée
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_aeroport_arrivee")
    private Aeroport aeroportArrive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_avion")
    private Avion avion;


    public Vol() {
    }


    // Constructeur
    public Vol(double temps, String disponibilite, int nb_place, Aeroport aeroportDepart, Aeroport aeroportArrivee, Avion avion) {
        this.temps = temps;
        if (temps < 2) {
            this.prix = 500;
        }
        this.prix = 80 * temps;
        this.disponibilite = disponibilite;
        this.nb_place = nb_place;
        this.aeroportDepart = aeroportDepart;
        this.aeroportArrive = aeroportArrivee;
        this.avion = avion;
    }

    // Getters et Setters
    public Aeroport getAeroportArrive() {
        return aeroportArrive;
    }

    public Aeroport getAeroportDepart() {
        return aeroportDepart;
    }

    public Avion getAvion() {
        return avion;
    }

    public double getPrix() {
        return prix;
    }

    public int getNb_place() {
        return nb_place;
    }

    public int getVol_id() {
        return vol_id;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setAeroportArrive(Aeroport aeroportArrive) {
        this.aeroportArrive = aeroportArrive;
    }

    public void setAeroportDepart(Aeroport aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public void setTemps(double temps) {
        this.temps = temps;
        if (temps < 2) {
            this.prix = 500;
        }
        this.prix = 80 * temps;
    }

    public double getTemps() {
        return temps;
    }

    public void setVol_id(int vol_id) {
        this.vol_id = vol_id;
    }
}
