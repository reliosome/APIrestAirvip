package com.airvip.APIrest.repository;

import com.airvip.APIrest.classes.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.vol.vol_id = :volId")
    List<Reservation> findByVolId(@Param("volId") int volId);
    @Query("SELECT r FROM Reservation r WHERE r.utilisateur.id_utilisateur = :utilisateurId")
    List<Reservation> findByUtilisateurId(@Param("utilisateurId") int utilisateurId);


}
