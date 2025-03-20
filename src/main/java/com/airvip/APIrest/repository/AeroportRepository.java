package com.airvip.APIrest.repository;
import com.airvip.APIrest.classes.Aeroport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeroportRepository extends JpaRepository<Aeroport,Integer> {
    // Tu peux ajouter des méthodes personnalisées ici
}

