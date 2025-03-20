package com.airvip.APIrest.classes;
import jakarta.persistence.*;

@Entity
@Table(name = "Aeroport", schema = "dbo")
public class Aeroport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private int id_aeroport;

    @Column(nullable = false)
    private String code_IATA;
    @Column(nullable = false) // Champ obligatoire
    private String ville;
    @Column(nullable = false)
    private String pays;
    @Column(nullable = false)
    private int distance_montreal;

    public Aeroport() {}

    public Aeroport(String ville, String pays, int distance_montreal) {
        this.ville = ville;
        this.pays = pays;
        this.distance_montreal = distance_montreal;
    }

    public int getId_aeroport() {
        return id_aeroport;
    }

    public void setId_aeroport(int id_aeroport) {
        this.id_aeroport = id_aeroport;
    }

    public String getCode_IATA() {
        return code_IATA;
    }

    public void setCode_IATA(String code) {
        this.code_IATA = code;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public int getDistance_montreal() {
        return distance_montreal;
    }

    public void setDistance_montreal(int distanceMTL) {
        this.distance_montreal = distanceMTL;
    }
}
