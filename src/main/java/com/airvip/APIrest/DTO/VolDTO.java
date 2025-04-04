package com.airvip.APIrest.DTO;

public class VolDTO {

    private double temps;
    private String disponibilite;
    private int nb_place;
    private int fk_aeroport_arrivee;
    private int fk_aeroport_depart;
    private int fk_avion;

    public double getTemps() {
        return temps;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public int getNb_place() {
        return nb_place;
    }

    public int getFk_avion() {
        return fk_avion;
    }

    public int getFk_aeroport_arrivee() {
        return fk_aeroport_arrivee;
    }

    public int getFk_aeroport_depart() {
        return fk_aeroport_depart;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setTemps(double temps) {
        this.temps = temps;
    }

    public void setFk_avion(int avion_id) {
        this.fk_avion = avion_id;
    }

    public void setFk_aeroport_arrivee(int arrivee_id) {
        this.fk_aeroport_arrivee = arrivee_id;
    }

    public void setFk_aeroport_depart(int depart_id) {
        this.fk_aeroport_depart = depart_id;
    }
}

