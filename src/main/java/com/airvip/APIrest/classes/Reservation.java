package com.airvip.APIrest.classes;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reservation", schema = "dbo")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "vol_id", nullable = false)
    private Vol vol;

    @Column(name = "paiement_effectue")
    private Boolean paiementEffectue = false;

    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;

    public Reservation() {
        // Default constructor
    }

    // Constructor with current time
    public Reservation(Utilisateur utilisateur, Vol vol) {
        this.utilisateur = utilisateur;
        this.vol = vol;
        this.dateReservation = LocalDateTime.now();
        this.paiementEffectue = false;
    }

    // Constructor with custom date
    public Reservation(Utilisateur utilisateur, Vol vol, LocalDateTime dateReservation) {
        this.utilisateur = utilisateur;
        this.vol = vol;
        this.dateReservation = dateReservation;
        this.paiementEffectue = false;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public Boolean getPaiementEffectue() {
        return paiementEffectue;
    }

    public void setPaiementEffectue(Boolean paiementEffectue) {
        this.paiementEffectue = paiementEffectue;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }
}
