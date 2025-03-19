package com.airvip.APIrest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeroportRepository extends JpaRepository<Aeroport,Integer> {
    // Tu peux ajouter des méthodes personnalisées ici
}

